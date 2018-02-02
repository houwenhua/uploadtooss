$(function(){
	$("#nav").tree({
		url:"login_tree.action",
		lines:true,
		onClick: function(node){
			if(node.url){
				if($("#tabs").tabs("exists",node.text)){
					$("#tabs").tabs("select",node.text)
				}else{
					$("#tabs").tabs('add',{
						title:node.text,
						iconCls:node.iconCls,
						closable:true,
						content:'<iframe width="100%" height="100%" id="iframe" name="iframe" frameborder="0" scrolling="auto" src="'+node.url+'"></iframe>'
					});
				}
			}
		}
	});
	$('#tabs').tabs({
		fit:true,
		border:false,
	});
})
function reloadDatagrid(title){
	if ($("#tabs").tabs('exists', title)) {
	　　　　　 $('#tabs').tabs('select', title);
			var tab = $("#tabs").tabs('getTab',title);//假设是tab
			var oBtn = $("iframe",tab).contents().find("#btn");
			oBtn.click();  //模拟点击事件，页面刷新，出发点击事件
	}
}
function reloadDatagridService(title){
	if ($("#tabs").tabs('exists', title)) {
	　　　　　 $('#tabs').tabs('select', title);
			var tab = $("#tabs").tabs('getTab',title);//假设是tab
			var oBtn = $("iframe",tab).contents().find("#btn");
			oBtn.click();  //模拟点击事件，页面刷新，出发点击事件
	}
}
function reloadDatagridStore(title){
	if ($("#tabs").tabs('exists', title)) {
	　　　　　 $('#tabs').tabs('select', title);
			var tab = $("#tabs").tabs('getTab',title);//假设是tab
			var oBtn = $("iframe",tab).contents().find("#btn");
			oBtn.click();  //模拟点击事件，页面刷新，出发点击事件
	}
}
function reloadDatagridOperator(title){
	if ($("#tabs").tabs('exists', title)) {
	　　　　　 $('#tabs').tabs('select', title);
			var tab = $("#tabs").tabs('getTab',title);//假设是tab
			var oBtn = $("iframe",tab).contents().find("#btn");
			oBtn.click();  //模拟点击事件，页面刷新，出发点击事件
	}
}
function reloadDatagridWaiter(title){
	if ($("#tabs").tabs('exists', title)) {
	　　　　　 $('#tabs').tabs('select', title);
			var tab = $("#tabs").tabs('getTab',title);//假设是tab
			var oBtn = $("iframe",tab).contents().find("#btn");
			oBtn.click();  //模拟点击事件，页面刷新，出发点击事件
	}
}
function reloadDatagridShopManager(title){
	if ($("#tabs").tabs('exists', title)) {
	　　　　　 $('#tabs').tabs('select', title);
			var tab = $("#tabs").tabs('getTab',title);//假设是tab
			var oBtn = $("iframe",tab).contents().find("#btn");
			oBtn.click();  //模拟点击事件，页面刷新，出发点击事件
	}
}