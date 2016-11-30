<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>

<%@include file="/public/head.jspf"%>

<script type="text/javascript">

	function bikeDetails(){
		$('#win').window({
			width:500,
			height:300,
			title:'车辆详情',
			cache:false,
			content:'<iframe src="${pageContext.request.contextPath}/base/station_bike" frameborder="0" width="100%" height="100%"/>'
		});
	}
	$(function(){
		var roles="${sessionScope['roles']}";//获取角色
		var resources="${sessionScope['resources']}";//获取权限
		$('#dg').datagrid({    
		    url:'${pageContext.request.contextPath}/base/stationAction_queryByPage.action',
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
					if(resources.indexOf('040301')==-1){
						$('#add').css('display','none');
					}else{
						$('#win').window({
			 				width:330,
			 				height:250,
			 				title:'站点添加',
			 				cache:false,
			 				content:'<iframe src="${pageContext.request.contextPath}/base/station_add" frameborder="0" width="100%" height="100%"/>'
			 			});
					}
				}
			},{
				id:'delete',
				iconCls:'icon-remove',
				text:'删除',
				handler:function(){
					if(resources.indexOf('040302')==-1){
						$('#delete').css('display','none');
					}else{
						var row=$("#dg").datagrid("getSelected");
						if(row){
							$.messager.confirm('确认对话框', '您想要删除所选数据吗？', function(r){
								if (r){
									$.ajax({
										url:'${pageContext.request.contextPath}/base/stationAction_delete.action',
										method:'POST',
										dataType:'json',
										data:{'stationSn':row.stationSn},
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
					if(resources.indexOf('040303')==-1){
						$('#update').css('display','none');
					}else{
						var row=$("#dg").datagrid("getSelected");
						if(row){
							$('#win').window({
				 				width:330,
				 				height:250,
				 				title:'站点修改',
				 				cache:false,
				 				content:'<iframe src="${pageContext.request.contextPath}/base/station_update" frameborder="0" width="100%" height="100%"/>'
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
			}],
		    columns:[[    
		        {field:'stationSn',title:'站点编号',width:'20%',align:'center'},    
		        {field:'stationName',title:'站点名称',width:'20%',align:'center'},    
		        {field:'location',title:'站点位置',width:'40%',align:'center'},
		        {field:'bikes',title:'站点车辆',width:'20%',align:'center',formatter:function(value,row,index){
					return "<a href='#' onclick='bikeDetails()' style='text-decoration:none'>"+"车辆详情"+"["+value+"]"+"</a>";
			    }}   
		    ]]    
		}); 
		
		//权限设置
		if(resources.indexOf('040301')==-1){
			$('#add').css('display','none');
		}
		if(resources.indexOf('040302')==-1){
			$('#delete').css('display','none');
		}
		if(resources.indexOf('040303')==-1){
			$('#update').css('display','none');
		}
	})
		

</script>
</head>
<body style="margin:1px;">
    <table id="dg"></table> 
    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div> 
</body>
</html>