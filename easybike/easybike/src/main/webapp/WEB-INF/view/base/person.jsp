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
					if(resources.indexOf('040101')==-1){
						$('#add').css('display','none');
					}else{
						$('#win').window({
			 				width:330,
			 				height:250,
			 				title:'人员添加',
			 				cache:false,
			 				content:'<iframe src="${pageContext.request.contextPath}/base/person_add" frameborder="0" width="100%" height="100%"/>'
			 			});
					}
				}
			},{
				id:'delete',
				iconCls:'icon-remove',
				text:'删除',
				handler:function(){
					if(resources.indexOf('040102')==-1){
						$('#delete').css('display','none');
					}else{
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
				}
			},{
				id:'update',
				iconCls:'icon-edit',
				text:'修改',
				handler:function(){
					if(resources.indexOf('040103')==-1){
						$('#update').css('display','none');
					}else{
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
				}
			},{
				id:'import',
				iconCls:'icon-excel',
				text:'人员导入',
				handler:function(){
					if(resources.indexOf('040104')==-1){
						$('#import').css('display','none');
					}else{
						$("#win").window({
							width:360,
							height:150,
							title:"Excel导入",
							cache:false,
							content:'<iframe src="${pageContext.request.contextPath}/base/excel" frameborder="0" width="100%" height="100%"/>'
						});
					}
				}
			},{
				id:'export',
				iconCls:'icon-excel',
				text:'人员导出',
				handler:function(){
					if(resources.indexOf('040105')==-1){
						$('#export').css('display','none');
					}else{
						$.messager.confirm('导出确认','您确定要导出人员信息吗？',function(r){
							var form=$("<form>");
							form.attr("style","display:none");
							form.attr("target","");
							form.attr("method","post");
							form.attr("action","base/personAction_export.action");
							//将表单放入body
							$("body").append(form);
							form.submit();//提交表单
						})
					}
				}
			}],
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
		
		
		//权限设置
		if(resources.indexOf('040101')==-1){
			$('#add').css('display','none');
		}
		if(resources.indexOf('040102')==-1){
			$('#delete').css('display','none');
		}
		if(resources.indexOf('040103')==-1){
			$('#update').css('display','none');
		}
		if(resources.indexOf('040104')==-1){
			$('#import').css('display','none');
		}
		if(resources.indexOf('040105')==-1){
			$('#export').css('display','none');
		}
	})
		

</script>
</head>
<body style="margin:1px;">
	<input id="type" type="hidden" value="person"/>
    <table id="dg"></table> 
    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div> 
</body>
</html>