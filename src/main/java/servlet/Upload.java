package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory; 
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;

public class Upload extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(Upload.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fileUpload(request, response);
	}
	private void fileUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//客户端网页控制为UTF-8
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/heml;charset=utf-8");
		
		// 在解析请求之前先判断请求类型是否为文件上传类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);		
        // 文件上传处理工厂
        FileItemFactory factory = new DiskFileItemFactory();
        // 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 开始解析请求信息
        List items = null;
        try {
            items = upload.parseRequest(req);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }

        // 对所有请求信息进行判断
        Iterator iter = items.iterator();
        String message = "上传失败";
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            //得到文本信息
            if (item.isFormField()) {
            	if("txtName".equals(item.getFieldName())){
            		 String fileName = item.getString("utf-8");
            		 //打印文本信息
            		 logger.info(fileName);
            	}  
            }
            // 得到文件信息
            else {
                String fileName = item.getName();
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
                // endpoint深圳
        		String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        		// 云账号AccessKey
        		String accessKeyId = "LTAIpaLT0jc5UQie";
        		String accessKeySecret = "ZFUVLUDvCEJJkMbZXAOGgfpnOgvyv0";
        		// 创建OSSClient实例
        		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        		// 上传文件流
        		InputStream inputStream = item.getInputStream();
        		ossClient.putObject("hwhyj", fileName, inputStream);
        		// 关闭client
        		//获得url
        		//设置URL过期时间为10年  3600l* 1000*24*365*10
        	    Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        		URL url = ossClient.generatePresignedUrl("hwhyj", fileName, expiration);
        		
        		ossClient.shutdown();
        		message = "上传成功了"+url.toString();
            }
        }
        req.setAttribute("message", message);
        req.getRequestDispatcher("/ok.jsp").forward(req, resp);
    }
	
}
