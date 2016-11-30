<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>

<%@include file="/public/head.jspf"%>

<script type="text/javascript">
		function editMaintenance(){
			$('#win').window({
				width:600,
				height:300,
				title:'操作',
				cache:false,
				content:'<iframe src="${pageContext.request.contextPath}/daily/maintenance_operate" frameborder="0" width="100%" height="100%"/>'
			});
		}
	$(function(){	
		$('#dg').datagrid({
			url:'${pageContext.request.contextPath}/daily/maintenanceAction_queryByPage.action',
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
				        {field:'maintenanceSn',title:'维修编号',width:'10%',align:'center',hidden:true},    
				        {field:'bikeSn',title:'车辆编号',width:'10%',align:'center'},    
				        {field:'reporterSn',title:'报修人',width:'10%',align:'center'},    
				        {field:'reportDatetime',title:'报修时间',width:'10%',align:'center'},
				        {field:'reportMark',title:'报修说明',width:'10%',align:'center'},
				        {field:'repairmanSn',title:'维修负责人',width:'10%',align:'center',},
				        {field:'repairDatetime',title:'维修时间',width:'10%',align:'center',},
				        {field:'repairMark',title:'维修说明',width:'10%',align:'center',},
				        {field:'isrepairable',title:'车辆状态',width:'10%',align:'center',},
				        {field:'operate',title:'维修状态',width:'10%',formatter:function(value,row,index){
				        	if(row.repairmanSn=="" || row.repairmanSn==null){
				        		return "<a  href='#' onclick='editMaintenance()' data-options='iconCls:'icon-edit'' class='easyui-linkbutton' style='text-decoration:none'>"+"操作"+"</a>";
				        	}else{
				        		return "<p>"+"已维修"+"</p>";
				        	}	
				        }}
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