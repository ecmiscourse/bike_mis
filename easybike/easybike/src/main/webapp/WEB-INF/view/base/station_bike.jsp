<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>

<%@include file="/public/head.jspf"%>

<script type="text/javascript">
	$(function(){
		var row=parent.$('#dg').datagrid('getSelected');//父级dg选中行
		$('#dg').datagrid({    
		    url:'${pageContext.request.contextPath}/base/bikeAction_getByStationSn.action',
			queryParams:{stationSn:row.stationSn},
		    fitColumns:true,
		    fit:true,
		    striped:true,/*斑马线*/
		    nowarp:true,/*数据同一行*/ 
		    loadmsg:'请等待',
		    rownumbers:true,
		    singleSelect:true,
		    /*分页*/
			pagination:true,
			pageNumber:1,
			pageSize:15,
			pageList:[15,30,50,100], 
		    columns:[[      
		        {field:'bikeSn',title:'车辆编号',width:'50%',align:'center'},    
		        {field:'startDate',title:'投入使用日期',width:'50%',align:'center'},      
	    	]]    
		}); 
		
	})
		

</script>
</head>
<body style="margin:1px;">
    <table id="dg"></table> 
    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div> 
</body>
</html>