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
		    url:'base/personAction_queryByPage.action',
		    fitColumns:true,
		    fit:true,
		    striped:true,/*斑马线*/
		    nowarp:true,/*数据同一行*/ 
		    loadmsg:'请等待',
		    rownumbers:true,
		    singleSelect:false,
		    /*分页*/
			pagination:true,
			pageNumber:1,
			pageSize:15,
			pageList:[15,30,50,100],   
		    columns:[[    
		        {field:'personSn',title:'学号',width:'25%',align:'center'},    
		        {field:'personName',title:'姓名',width:'25%',align:'center'},    
		        {field:'sex',title:'性别',width:'25%',align:'center',formatter:function(value,row,index){
					if(value==0){
						return '男';
					}else{
						return '女';
					}
			    }},
		        {field:'cellphoneNumber',title:'联系方式',width:'25%',align:'center'}   
		    ]]    
		}); 
		
	})
		

</script>
</head>
<body style="margin:1px;">
    <table id="dg"></table>  
</body>
</html>