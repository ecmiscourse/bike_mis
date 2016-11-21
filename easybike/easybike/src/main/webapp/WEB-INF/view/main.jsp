<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layout.js" charset="utf-8"></script>
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
            <div id="divTitleBar" title="默认页面" >
            	哈哈哈
            </div>
        </div>
    </div>
</body>
</html>