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
  		url:'circulate/lendAndReturnRecordAction_queryByPage.action',
  		title:'借车记录表',
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
				{field:'recordSn',title:'借还记录编号',width:100,hidden:true,align:'center'}, 
				{field:'lendStationSn',title:'借车站点',width:100,align:'center',hidden:true},
				{field:'lendStationName',title:'借车站点',width:100,align:'center'},
				{field:'bikeSn',title:'借出车辆',width:100,align:'center'},
				{field:'studentId',title:'借车人学号',width:100,align:'center'},
				{field:'studentName',title:'借车人姓名',width:100,align:'center'},	
				{field:'phoneNumber',title:'借车人联系方式',width:100,align:'center'},	
				{field:'lendDateTime',title:'借出时间',width:100,align:'center'},	
				{field:'isHasReturned',title:'是否归还',width:100,align:'center'},	
				{field:'lendPerson',title:'借出操作人',width:100,align:'center',hidden:true},	
				{field:'lendPersonName',title:'借出操作人',width:100,align:'center'},	
  		     ]],
  		   toolbar: [{
  			   	id:'add',
		    	text:'新增',
				iconCls: 'icon-add',
				handler: function(){
					if(resources.indexOf('020101')==-1){
						$('#add').css('display','none');
					}else{
					$('#win').window({
						width:380,
		 				height:330,
		 				title:'借车记录添加',
		 				cache:false,
		 				content:'<iframe src="${pageContext.request.contextPath}/circulate/bicycleLend_add" frameborder="0" width="100%" height="100%"/>'
	
					});
				}
				}
			},{
				id:'delete',
				iconCls:'icon-remove',
				text:'删除',
				handler:function(){
					if(resources.indexOf('020102')==-1){
						$('#delete').css('display','none');
					}else{
					var row=$("#dg").datagrid("getSelected");
					if(row){
						console.log(row);
						if(row.recordSn==null){
							$.messager.alert('我的提示','对不起，您无法删除自己！','warning');
						}else{
							$.messager.confirm('确认对话框', '您想要删除所选数据吗？', function(r){
								if (r){
									$.ajax({
										url:'${pageContext.request.contextPath}/circulate/lendAndReturnRecordAction_delete.action',
										method:'POST',
										dataType:'json',
										data:{'recordSn':row.recordSn},
										success:function(data){
											if(data.status=="ok"){
												$.messager.alert('我的提示','删除成功！','info');
												$("#dg").datagrid("reload");						
											}else{
												$.messager.alert('我的提示','删除失败！','error');
											}
										}
									})
								}
							});	
						}					
					}else{
						$.messager.show({
							title:'我的提示',
							msg:'请先选择一条记录！',
							timeout:1000,
							showType:'show',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop+200,
								bottom:''
							}
						})
					}
					
					}
					
				}
			},{
				id:'update',
				iconCls:'icon-edit',
				text:'修改',
				handler:function(){
					if(resources.indexOf('020103')==-1){
						$('#update').css('display','none');
					}else{
					var row=$("#dg").datagrid("getSelected");
					if(row){
						$('#win').window({
			 				width:280,
			 				height:400,
			 				title:'信息修改',
			 				cache:false,
			 				content:'<iframe src="${pageContext.request.contextPath}/circulate/bicycleLend_update" frameborder="0" width="100%" height="100%"/>'
			 			});
					}else{
						$.messager.show({
							title:'我的提示',
							msg:'请先选择一条记录！',
							timeout:1000,
							showType:'show',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop+200,
								bottom:''
							}
						});
					} 
				
					}
				
				
				}
			}
			
  		   ],
  	});
  	
  	
  	
  	
  	
  //权限设置
	if(resources.indexOf('020101')==-1){
		$('#add').css('display','none');
	}
	if(resources.indexOf('020102')==-1){
		$('#delete').css('display','none');
	}
	if(resources.indexOf('020103')==-1){
		$('#update').css('display','none');
	}
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
});
      
      
      </script>
</head>
<body>
	<table id="dg" data-options="width:1000"></table>
	 <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div> 
</body>
</html>