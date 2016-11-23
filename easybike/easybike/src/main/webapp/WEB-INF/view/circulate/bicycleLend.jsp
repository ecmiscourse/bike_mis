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
  		url:'circulate/lendAndReturnRecordAction_queryByPage.action',
  		title:'用户表',
  		 pagination:true,
    	    pageNumber:1,
    	    pageSize:15,
    	    pageList:[15,30,45],
    	    striped:true,
    	    loadMsg:'正在努力加载中...' ,
    	    rownumbers:true,
  		 columns:[[
				{field:'id',title:'id',width:100,checkbox:true,editor:{type:'numberbox',options:{precision:1}}}, 
				{field:'recordSn',title:'借还记录编号',width:100},   
				{field:'bike',title:'借出车辆',width:100},
				{field:'studentId',title:'借车人学号',width:100},
				{field:'studentName',title:'借车人姓名',width:100},	
				{field:'phoneNumber',title:'借车人联系方式',width:100},	
				{field:'lendDateTime',title:'借出时间',width:100},	
				{field:'isHasReturned',title:'是否归还',width:100},	
				{field:'lendPerson',title:'借出人',width:100},	
  		     ]],
  		   toolbar: [{
		    	text:'新增借车记录',
				iconCls: 'icon-add',
				handler: function(){
					$('#dlg').dialog('open').dialog('setTitle','新借车记录');
				}
			}],
  	});
  	
  	
});
      
      </script>
</head>
<body>
	<table id="dg" data-options="width:1000"></table>
</body>
</html>