<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<script type="text/javascript">

 function Details(){
	$('#win').window({
		width:800,
		height:300,
		title:'历史记录',
		cache:false,
		content:'<iframe src="${pageContext.request.contextPath}/daily/trace" frameborder="0" width="100%" height="100%"/>'
	});
} 
$(function(){
	var roles="${sessionScope['roles']}";//获取角色
	var resources="${sessionScope['resources']}";//获取权限
	
	$('#dg').datagrid({    
	    url:'${pageContext.request.contextPath}/daily/traceAction_queryAllbikes.action',
	    fitColumns:true,
	    fit:true,
	    striped:true,/*斑马线*/
	    nowarp:true,/*数据同一行*/ 
	    loadmsg:'请等待',
	    rownumbers:true,
	    singleSelect:true,
		pagination:true,
		pageNumber:1,
		pageSize:15,
		pageList:[15,30,50,100], 
		columns:[[
		          {field:'bikeSn',title:'车辆编号',width:'40%',align:'center'},
		          {field:'status',title:'当前状态',width:'40%',align:'center',formatter:function(value,row,index){
		        	  if(value==0){
		        		  return "可借";
		        	  }else if(value==1){
		        		  return "借出"
		        	  }else if (value==2){
		        		  return "维修";
		        	  }else if(value==3){
		        		  return "报废";
		        	  }
		          }},
		          {field:'details',title:'历史记录',width:'20%',align:'center',formatter:function(value,row,index){
		        		  return "<a  href='#' onclick='Details()' data-options='iconCls:'icon-edit'' class='easyui-linkbutton' style='text-decoration:none'>"+"查看历史记录"+"</a>";				        		
		        	}}
			]],
			 toolbar:[{
					text:'<input id="search" type="tb"  style="width:300px">'
				}]
		});
	//提交 
	$('#search').textbox({    
	    buttonText:'搜索',    
	    iconCls:'icon-search', 
	    iconAlign:'left' ,
	    prompt:'请输入自行车编号进行查询',
	    onClickButton:function(){
	    	$('#dg').datagrid('reload',{
	    		bikeSn:$('#search').textbox('getValue')
	    	})
	    }
	})

})

</script>
</head>
<body style="margin:1px;">

	<table id="dg"></table>
	 <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>  
</body>

</html>