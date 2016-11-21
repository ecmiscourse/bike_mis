<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>

<%@include file="/public/head.jspf"%>

<script type="text/javascript">

	$(function(){
		$('#dg').datagrid({    
		    url:'',    
		    columns:[[    
		        {field:'code',title:'代码',width:100},    
		        {field:'name',title:'名称',width:100},    
		        {field:'price',title:'价格',width:100,align:'right'}    
		    ]]    
		}); 
		
	})
		

</script>
</head>
<body style="margin:1px;">
    <table id="dg"></table>  
</body>
</html>