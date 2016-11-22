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
		    url:'${pageContext.request.contextPath}/base/personAction_queryByPage.action',
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
			/*按钮*/
			toolbar:[{
				id:'add',
				iconCls:'icon-add',
				text:'添加',
				handler:function(){
					$('#win').window({
		 				width:330,
		 				height:250,
		 				title:'人员添加',
		 				cache:false,
		 				content:'<iframe src="${pageContext.request.contextPath}/base/person_add" frameborder="0" width="100%" height="100%"/>'
		 			});
				}
			},{
				id:'delete',
				iconCls:'icon-remove',
				text:'删除',
				handler:function(){
					var row=$("#dg").datagrid("getSelected");
					if(row){
						if(row.personSn=="${sessionScope['personSn']}"){
							$.messager.alert('我的提示','对不起，您无法删除自己！','warning');
						}else{
							$.messager.confirm('确认对话框', '您想要删除所选数据吗？', function(r){
								if (r){
									$.ajax({
										url:'${pageContext.request.contextPath}/base/personAction_delete.action',
										method:'POST',
										dataType:'json',
										data:{'personSn':row.personSn},
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
			},{
				id:'update',
				iconCls:'icon-edit',
				text:'修改',
				handler:function(){
					var row=$("#dg").datagrid("getSelected");
					if(row){
						$('#win').window({
			 				width:330,
			 				height:250,
			 				title:'人员修改',
			 				cache:false,
			 				content:'<iframe src="${pageContext.request.contextPath}/base/person_update" frameborder="0" width="100%" height="100%"/>'
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
			},{
				id:'import',
				iconCls:'icon-excel',
				text:'导入',
				handler:function(){
					console.log('ok');
				}
			},{
				id:'export',
				iconCls:'icon-excel',
				text:'导出',
				handler:function(){
					console.log('ok');
				}
			}],
		    columns:[[    
		        {field:'bikeSn',title:'车辆编号',width:'25%',align:'center'},    
		        {field:'startDate',title:'投入使用日期',width:'25%',align:'center'},    
		        {field:'status',title:'当前状态',width:'25%',align:'center'},
		        {field:'cellphoneNumber',title:'所在站点',width:'25%',align:'center'}   
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