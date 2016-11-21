<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我借出的</title>
	<%@include file="/public/head.jspf"%>
	<script type="text/javascript">
	$(function(){
		$('#box').datagrid({    
		    url:'',    
		    columns:[[    
		        {field:'',title:'',width:100},    
		        {field:'name',title:'Name',width:100},    
		        {field:'price',title:'Price',width:100,align:'right'},
		        {field:'price',title:'Price',width:100,align:'right'},
		        {field:'price',title:'Price',width:100,align:'right'}
		    ]],
		    toolbar:'#search',
		});  
	})
		
		</script>	
</head>
<body>
	<table id="box"></table>
	<div id="search">
		<div>
			输入自行车编号：<input type="text" name="">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>