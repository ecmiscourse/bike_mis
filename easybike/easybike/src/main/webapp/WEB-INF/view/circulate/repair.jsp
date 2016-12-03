<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<script type="text/javascript">
$(function(){
	var roles="${sessionScope['roles']}";//获取角色
	var resources="${sessionScope['resources']}";//获取权限
  	$('#dg').datagrid({
  		url:'${pageContext.request.contextPath}/daily/maintenanceAction_queryReportMessage.action',
  		title:'报修记录表',
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
				{field:'maintenanceSn',title:'维修编号',width:'20%',align:'center',hidden:true},    
				{field:'bikeSn',title:'车辆编号',width:'20%',align:'center'},    
				{field:'reporterName',title:'报修人',width:'20%',align:'center'},    
				{field:'reportDatetime',title:'报修时间',width:'20%',align:'center'},
				{field:'reportMark',title:'报修说明',width:'30%',align:'center'},
				{field:'isrepairable',title:'维修结果',width:'10%',align:'center',formatter:function(value,row,index){
		        	if(value==true){
		        		return "完成";
		        	}else if(value==false){
		        		return "报废处理";
		        	}else{
		        		return "处理中";
		        	}
		        }}
  		     ]],
  		   toolbar: [{
  			   	id:'add',
		    	text:'报修',
				iconCls: 'icon-add',
				handler: function(){
					if(resources.indexOf('020301')==-1){
						$('#add').css('display','none');
					}else{
						$('#win').window({
							width:360,
			 				height:250,
			 				title:'报修',
			 				cache:false,
			 				content:'<iframe src="${pageContext.request.contextPath}/circulate/repair_add" frameborder="0" width="100%" height="100%"/>'
		
						});
					}					
				}
			}],
  	});
  	
  //权限设置
	if(resources.indexOf('020301')==-1){
		$('#add').css('display','none');
	}
  	
  	
});
      
      </script>
</head>
<body>
<table id="dg"></table>  
     <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div> 
</body>
</html>