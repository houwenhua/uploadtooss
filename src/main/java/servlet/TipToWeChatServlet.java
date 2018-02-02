package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TipToWeChatServlet
 */
public class TipToWeChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userAgent = request.getHeader("User-Agent");
		System.out.println(userAgent);
		String url = "http://app.91.com/soft/iTunesDetail.aspx?identifier=com.tencent.xin";
		if("android".contains(userAgent)){
			url="http://shouji.baidu.com/software/item?pid=2786482313&from=web_alad_multi";
		}
		if("iphone".contains(userAgent)){
			url="http://app.91.com/soft/iTunesDetail.aspx?identifier=com.tencent.xin";
		}
		response.sendRedirect(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
