package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet(name = "HeaderServlet",urlPatterns = "/headerServlet")
public class HeaderServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(Upload.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    /**
     * 获取用户真实ip不能用request.getRemoteAddr(),有可能用户用了代理软件，避免了真实的ip，如果用户用了
     * 多级代理，x-forwarded-for是一串ip值
     *
     *
     * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得用户ip
        String ip = getRealIp(request);
        logger.info("真正的ip是:"+ip);

        //设置cookie
        Cookie cookie = new Cookie("ServletSaveTime","save");
        //过期时间为1天
        cookie.setMaxAge(3600*24);
        cookie.setPath("/");
        response.addCookie(cookie);

        //获得客户端的cookie值
        Cookie[] cookies = request.getCookies();
        Map<String,String> map = new HashMap<String,String>();
        map.put("realIp",ip);
        if(cookies!=null){
            for(int i = 0; i < cookies.length; i++){
                Cookie c = cookies[i];
                map.put(c.getName(),c.getValue());
            }
        }
        request.setAttribute("map",map);
        request.getRequestDispatcher("header.jsp").forward(request,response);
    }
    @Override
    protected long getLastModified(HttpServletRequest req) {
    	HttpSession session = req.getSession();
        String path = req.getServletPath();
        int index = path.lastIndexOf('/');
        path = path.substring(index + 1);
        Date lastModifyDate=(Date)session.getAttribute(path);
        if(lastModifyDate==null){
            lastModifyDate=new Date();
            session.setAttribute(path, lastModifyDate);
        }
        //实现三十秒内加载都是进行的缓存
        if(lastModifyDate!=null){
            Date dateNow = new Date();
            long longDate = dateNow.getTime();
            if(lastModifyDate.getTime()+30000<longDate) {
                lastModifyDate = dateNow;
                session.setAttribute(path, lastModifyDate);
            }
        }
        return lastModifyDate.getTime();
    }
    private String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            //多级反向代理后，第一i才是真正的ip
            if(ip.indexOf(",")!=-1){
                ip = ip.split(",")[0];
            }
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}