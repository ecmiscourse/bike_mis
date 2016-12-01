<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<script type="text/javascript">

$(function(){
	var row=parent.$('#dg').datagrid('getSelected');
	$('#dg').datagrid({    
	    url:'${pageContext.request.contextPath}/daily/traceAction_queryAllTrace.action',
	    queryParams:{bikeSn:row.bikeSn},
	    fitColumns:true,
	    fit:true,
	    striped:true,/*斑马线*/
	    nowarp:false,/*数据同一行*/ 
	    loadmsg:'请等待',
	    rownumbers:true,
	    singleSelect:true,
		pagination:true,
		pageNumber:1,
		pageSize:15,
		pageList:[15,30,50,100], 
		columns:[[
		          {field:'recordSn',title:'记录编号',align:'center',hidden:true},
		          {field:'studentId',title:'借车人',width:'8%',align:'center'},
		          {field:'lendDateTime',title:'借出时间',width:'16%',align:'center'},
		          {field:'returnDateTime',title:'归还时间',width:'16%',align:'center'},
		          {field:'isHasReturned',title:'是否归回',width:'10%',align:'center'},
		          {field:'lendPerson',title:'借出操作人',width:'15%',align:'center'},
		          {field:'lendStationSn',title:'借出站点',width:'10%',align:'center'},
		          {field:'returnPerson',title:'归还操作人',width:'15%',align:'center'},
    			  {field:'returnStationSn',title:'归还站点',width:'10%',align:'center'} 
			]]
		});


})

</script>
</head>
<body style="margin:1px;">

	<table id="dg"></table> 
</body>

</html>