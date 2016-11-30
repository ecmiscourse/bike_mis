<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>

<%@include file="/public/head.jspf"%>

<script type="text/javascript">

		function diverge(){
			$('#win').window({
				width:500,
				height:200,
				title:'车辆调拨',
				cache:false,
				content:'<iframe src="${pageContext.request.contextPath}/daily/stations_detail" frameborder="0" width="100%" height="100%"/>'
			});
		}

	$(function(){	
		$('#dg').datagrid({
			url:'${pageContext.request.contextPath}/daily/distributeAction_queryByPage.action',
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
			           {field:'stationSn',title:'站点编号',width:'20%',align:'center'}, 
				        {field:'stationName',title:'站点名称',width:'20%',align:'center'}, 
				        {field:'bikes',title:'所剩车辆',width:'20%',align:'center'}, 
				        {field:'diverge',title:'调入',width:'20%',align:'center',formatter:function(value,row,index){
				        		return "<a  href='#' onclick='diverge()' data-options='iconCls:'icon-edit'' class='easyui-linkbutton' style='text-decoration:none'>"+"调入"+"</a>";				        	
				        }}
				        ]]				        
		});	
		
		
	})
	

	
</script>
</head>
<body style="margin:1px;">
    <table id="dg"></table>  
    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true">
    </div> 
    
</body>

</html>