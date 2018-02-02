package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aliyun.oss.OSSClient;


public class UploadTo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		uploadImage(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	private void uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建文件解析对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建上传文件实例
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		
		//打印信息
		String message = "444";
		List<?> list = null;
		try {
			//解析request为fileitem，因为支持多文件上传
			list = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		//进行迭代
		Iterator<?> it = list.iterator();
		while(it.hasNext()){
			//获取文件名
			FileItem item = (FileItem) it.next();
			String fileName = item.getName();
			if(fileName != null) {
				//获得最后一个/的位置
				int index = fileName.lastIndexOf("/")+1;
				//获得真实文件名
				String relFileName = fileName.substring(index);
				//获得文件输入流
				InputStream is = item.getInputStream();
				// endpoint是深圳
        		String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        		// 云账号AccessKey
        		String accessKeyId = "LTAIpaLT0jc5UQie";
        		String accessKeySecret = "ZFUVLUDvCEJJkMbZXAOGgfpnOgvyv0";
        		// 创建OSSClient实例
        		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        		ossClient.putObject("hwhyj", fileName, is);
        		// 关闭client
        		
        		//获得url
        		// 设置URL过期时间为10年  3600l* 1000*24*365*10
        	    Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        		URL url = ossClient.generatePresignedUrl("hwhyj", fileName, expiration);
        		
        		ossClient.shutdown();
        		is.close();
        		message = url.toString();
			}
		}
		response.getWriter().println(message);
	}

}
