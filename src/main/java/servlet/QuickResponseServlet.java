package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * Servlet implementation class QuickResponseServlet
 * 
 * 生成二维码
 */
public class QuickResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ByteArrayOutputStream os = QRCode.from("http://localhost:8088/uploadtooss/TipToWeChatServlet").to(ImageType.PNG).stream();
		response.setContentType("image/png");
		response.setContentLength(os.size());
		OutputStream out = response.getOutputStream();
		out.write(os.toByteArray());
		out.flush();
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
