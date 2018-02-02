<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
</head>
<body>
	<form onsubmit="return checknull()" name="form1" action="${pageContext.request.contextPath}/uploadto" method="post" enctype="multipart/form-data">	    
	         <div style="border:5px solid red;width:400px;height:400px;margin:auto;">
		         <p>
		         	请输入：    <input type="text" name="txtName" id="txtName"><br>
		         	上传文件：<input type="file" name="file1" id="file1"><br>
		         	上传文件：<input type="file" name="file2" id="file2"><br> 
		            <input type="submit" value="提交">
		         </p>
	         </div>
	</form> 
</body>
<script type="text/javascript">
   function checknull(){
	   if(form1.file1.value=="")  {
	     alert("请选择文件！");  
	     return false;
	   } 
   }
</script>
</html>