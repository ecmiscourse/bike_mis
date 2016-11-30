<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo.png" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layout.js" charset="utf-8"></script>
<script type="text/javascript">
function edit(){
	$('#win1').window({
			width:330,
			height:220,
			title:'修改密码',
			cache:false,
			content:'<iframe src="${pageContext.request.contextPath}/myop/person_password" frameborder="0" width="100%" height="100%"/>'
	});
};
function infor(){
	$('#win2').window({
		width:330,
		height:250,
		title:'个人信息',
		cache:false,
		content:'<iframe src="${pageContext.request.contextPath}/myop/person_information" frameborder="0" width="100%" height="100%"/>'
	})
}
</script>
</head>
<body class="easyui-layout">
    <div id="divHeader" data-options="region:'north',border:false,href:'header'">
    </div>
    <div id="divMenuBar" data-options="region:'west',split:true,collapsed:false,title:'菜单栏',href:'menu'">
    </div>
    <div id="divFooter" data-options="region:'south',border:false,href:'footer'">
    </div>
    <div id="divContent" data-options="region:'center',title:false">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
            <div id="divTitleBar" title="欢迎来到校园公共自行车管理系统" data-options="href:'${pageContext.request.contextPath}/myop/home'">
            	
            </div>
        </div>
    </div>
    
    <!-- 修改密码 -->
    <div id="win1" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>
    </div>
    
    <!-- 个人信息 -->
    <div id="win2" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>
</body>
</html>