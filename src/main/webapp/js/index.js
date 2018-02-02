$(function(){
	/*$.ajax({
		url:'login_image.action',
		type:'POST',
		success:function(data){
			if(data){
				$("#img").attr("src",data);
				console.log(data);
				alert($("#img")[0].src);
			}else{
				
			}
		},
	});*/
	$("#login").dialog({
		width:300,
	    height:230,
	    title:'登录',
	    iconCls:'icon-lock',
	    modal:true,
	    buttons:'#btn',
	    closable: false,
	    draggable:false,
	});
	//管理员验证
	$("#loginname").validatebox({
		required:true,
		missingMessage:'请输入管理员账号',
		invalidmessage:'账号不能为空',
	});
	$("#pwd").validatebox({
		required:true,
		validType:'length[6,30]',
		missingMessage:'请输入管理员密码',
		invalidmessage:'密码不能为空',
	});
	$("#imageText").validatebox({
		required:true,
		missingMessage:'请输入验证码',
		invalidmessage:'验证码不能为空',
	});
	//点击登录
	$("#btn a").click(function(){
		if(!$("#loginname").validatebox('isValid')){
			$("#loginname").focus();
		}else if(!$("#pwd").validatebox('isValid')){
			$("#pwd").focus();
		}else if(!$("#imageText").validatebox('isValid')){
			$("#imageText").focus();
		}else{
			$.ajax({
				url:'login_login.action',
				type:'POST',
				data:{
					loginname:$("#loginname").val(),
					pwd:$("#pwd").val(),
					imageText:$("#imageText").val(),
				},
				beforeSend:function(){
					/*$.messager.progress({
						text:'正在加载.....'
					});*/
				},
				success:function(data){
					//var jsonObj = eval('(' + data + ')');
					if(data==1){
						window.location.href = "main.jsp";
					}else if(data==200){
						$.messager.alert("提示消息","验证码错误！！！","info");
					}else{
						$.messager.alert("提示消息","用户名或者密码错误！！！","info");
					}
				},
			})
		}
	});
	
})