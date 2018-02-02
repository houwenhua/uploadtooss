package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "JstlServlet",urlPatterns = "/jstlServlet")
public class JstlServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String,String> map = new HashMap<String,String>();
        List list = new ArrayList();
        Enumeration e = request.getHeaderNames();
        while(e.hasMoreElements()) {
            String name = (String)e.nextElement();
            map.put(name,request.getHeader(name));
            list.add(request.getHeader(name));
        }

        Cookie[] cookies = request.getCookies();
        Map<String,String> map1 = new HashMap<String,String>();
        List list1 = new ArrayList();
        if(cookies!=null){
            for(int i = 0; i < cookies.length; i++){
                Cookie c = cookies[i];
                map1.put(c.getName(),c.getValue());
                list1.add(c.getValue());
            }
        }
        request.setAttribute("map",map);
        request.setAttribute("map1",map1);
        request.setAttribute("list",list);
        request.setAttribute("list1",list1);
        request.getRequestDispatcher("jstl.jsp").forward(request,response);
    }
}
