<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务图片上传</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
<body>
	<div id="form1" style="width:400px;" class="easyui-panel" title="服务图片上传">
		<div style="padding:10px 0 10px 60px;">
			    <form id="ff" method="post" enctype="multipart/form-data">
			    	<table>
			    		<tr style="display:none;">
			    			<td>id:</td>
			    			<td><input type="hidden" name="id"></input></td>
			    		</tr> 
			    		<tr>
			    			<td>图片名:</td>
			    			<td><input id="fb" type="text" style="width:250px" name="image"></td>
			    		</tr>
			    		<tr>
			    			<td>注意:</td>
			    			<td style="color:red;">请选择合适的图片最好是JPG格式</td>
			    		</tr>
			    	</table>
			    </form>
		    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" iconCls="icon-upload" class="easyui-linkbutton" onclick="submitForm()">上传</a>
	    </div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script>
	$('#fb').filebox({    
	    buttonText: '选择图片', 
	    buttonAlign: 'right',
	    buttonIcon:'icon-search',
	    accept:'image/jpeg' 
	});
	function GetQueryString(name){
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)
	   	 return  decodeURI(r[2]); 
	    return null;
	}
	$("input[name='id']").val(GetQueryString("id"));
	function submitForm(){
		var val = $('#fb').filebox('getValue');
		if($.trim(val)==''){
			$.messager.alert('系统提示', '请选择图片', 'info');
			return false;
		}
		$('#ff').form('submit',{
			url:'${pageContext.request.contextPath}/UploadTo',
			onSubmit:function(param){
				$.messager.progress({
					text:'正在上传.....'
				});
				return $(this).form('validate');
				$.messager.progress('close');
			},
			success:function(data){
				if(data!=444){
					data=data.replace(/amp;/g,"");
					$.messager.progress('close');
					$.messager.alert('系统提示', '上传成功！！！确定后打开上传图片', 'info', function(){
						window.location.href = data;
					 });
				}else{
					 $.messager.alert('系统提示', '上传失败', 'error');
					 $.messager.progress('close');
				}
			}
		});
	}
	function clearForm(){
		$('#ff').form('clear');
	}
	function closeTab(title){
		var jq = top.jQuery;
		jq('#tabs').tabs('close', title);
	}
</script>
</html>