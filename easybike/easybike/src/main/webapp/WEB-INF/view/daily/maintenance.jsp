<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script>
	$('#dg').datagrid({
		url : 'daily/maint.action',
		fitColumns : true,
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		remoteSort : 'false',
		columns : [[
		             { field:'maintenance_sn', width:20% },
		             { field:'bike_sn', width:20% },
		             { field:'reporter_sn', width:20% },
		             { field:'report_mark', width:20% },
		             {field:'repairman_sn',width:20% }
		             ]]
	})
	
	</script>
</head>
<body>

	<div id="win">
		<table id="dg"></table>
	</div>
</body>
</html>