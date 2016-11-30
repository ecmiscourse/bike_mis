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
		    url:'${pageContext.request.contextPath}/base/bikeAction_queryByPage.action',
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
				text:'所在站点：<input id="cc" name="stationSn">'
			},{
				id:'add',
				iconCls:'icon-add',
				text:'添加',
				handler:function(){
					if(resources.indexOf('040201')==-1){
						$('#add').css('display','none');
					}else{
						$('#dd1').dialog('open');
					}		
				}
			},{
				id:'delete',
				iconCls:'icon-remove',
				text:'删除',
				handler:function(){
					if(resources.indexOf('040202')==-1){
						$('#delete').css('display','none');
					}else{
						var row=$("#dg").datagrid("getSelected");
						if(row){
							$.messager.confirm('确认对话框', '您想要删除所选数据吗？', function(r){
								if (r){
									$.ajax({
										url:'${pageContext.request.contextPath}/base/bikeAction_delete.action',
										method:'POST',
										dataType:'json',
										data:{'bikeSn':row.bikeSn},
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
					if(resources.indexOf('040203')==-1){
						$('#update').css('display','none');
					}else{
						var row=$("#dg").datagrid("getSelected");
						if(row){
							$('#ff2').form('load',{
								bikeSn:row.bikeSn,
								startDate:row.startDate
							});
							$('#dd2').dialog('open');
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
				text:'车辆导入',
				handler:function(){
					if(resources.indexOf('040204')==-1){
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
				text:'车辆导出',
				handler:function(){
					if(resources.indexOf('040205')==-1){
						$('#export').css('display','none');
					}else{
						$.messager.confirm('导出确认','您确定要导出所有车辆信息吗？',function(r){
							var form=$("<form>");
							form.attr("style","display:none");
							form.attr("target","");
							form.attr("method","post");
							form.attr("action","base/bikeAction_export.action");
							//将表单放入body
							$("body").append(form);
							form.submit();//提交表单
						})
					}
				}
			}],
		    columns:[[    
		        {field:'bikeSn',title:'车辆编号',width:'25%',align:'center'},    
		        {field:'startDate',title:'投入使用日期',width:'25%',align:'center'},    
		        {field:'status',title:'当前状态',width:'25%',align:'center',formatter: function(value,row,index){
					if (value==0){
						return '可借';
					} else if(value==1){
						return '借出';
					}else if(value==2){
						return '维修中';
					}else if(value==3){
						return '已报废，报废时间：'+row.endDate;
					}
				}},
		        {field:'station',title:'所在站点',width:'25%',align:'center'}   
		    ]]    
		}); 
		
		$("#ff1").form("disableValidation");
		$("#ff2").form("disableValidation");
		/*自定义验证*/
		$.extend($.fn.validatebox.defaults.rules, {    
		    length: {    
		        validator: function(value, param){
			        if(value.replace(/[^\d]/g,'').length == param[0] && value.replace(/[^\d]/g,'').length==value.length){
						return true;
				    }else{
				    	return false;   
					} 
		        },    
		        message: '数据非法 ！'   
		    }    
		}); 
		//ajax添加时验证自行车编号是否存在
		$("input",$("#bikeSn1").next("span")).blur(function(){
			$('#bikeSn1').textbox('enableValidation')
			if($('#bikeSn1').textbox('isValid')){
				$.ajax({
					url:'${pageContext.request.contextPath}/base/bikeAction_isExist.action',
					method:'POST',
					dataType:'json',
					data:{'bikeSn':$('#bikeSn1').textbox('getValue')},
					success:function(data){
						if(data.isExist==true){
							$.messager.alert('我的提示','该车辆编号已经存在！','warning');
							$('#bikeSn1').textbox('clear');						
						}
					}
				})
			}
		});

		//ajax修改时验证自行车编号是否存在
		$("input",$("#bikeSn2").next("span")).blur(function(){
			$('#bikeSn2').textbox('enableValidation')
			if($('#bikeSn2').textbox('isValid')&&$('#bikeSn2').textbox('getValue')!=$('#dg').datagrid('getSelected').bikeSn){
				$.ajax({
					url:'${pageContext.request.contextPath}/base/bikeAction_isExist.action',
					method:'POST',
					dataType:'json',
					data:{'bikeSn':$('#bikeSn2').textbox('getValue')},
					success:function(data){
						if(data.isExist==true){
							$.messager.alert('我的提示','该车辆编号已经存在！','warning');
							$('#bikeSn1').textbox('clear');						
						}
					}
				})
			}
		});

		//重置
		$("#reset1").click(function(){
			$("#ff1").form("reset");
		});
		//提交 
		$('#submit1').click(function(){
			$("#ff1").form("enableValidation");
			if($('#ff1').form('validate')){
				$('#ff1').form('submit', {    
				    url:'${pageContext.request.contextPath}/base/bikeAction_save.action',       
				    success:function(data){
				    	$("#ff1").form("disableValidation");    
				    	var result = eval('(' + data + ')');
				    	if(result.status=='ok'){
				    		$.messager.alert("提示信息","添加成功！");
							$("#ff1").form("reset");
							//关闭窗体
							$("#dd1").dialog("close");
							//刷新dg
							$("#dg").datagrid("reload");
					   	}else{
					   		$.messager.alert("提示信息","添加失败！",'error');
						}
				    }    
				});
			}
		})
		$('#submit2').click(function(){
			$("#ff2").form("enableValidation");
			if($('#ff2').form('validate')){
				$('#ff2').form('submit', {    
				    url:'${pageContext.request.contextPath}/base/bikeAction_update.action', 
				    queryParams:{oldBikeSn:$('#dg').datagrid('getSelected').bikeSn},      
				    success:function(data){
				    	$("#ff2").form("disableValidation");    
				    	var result = eval('(' + data + ')');
				    	if(result.status=='ok'){
				    		$.messager.alert("提示信息","修改成功！");
							$("#ff2").form("reset");
							//关闭窗体
							$("#dd2").dialog("close");
							//刷新dg
							$("#dg").datagrid("reload");
					   	}else{
					   		$.messager.alert("提示信息","修改失败！",'error');
						}
				    }    
				});
			}
		})

		//下拉框
		$('#cc').combobox({    
		    url:'${pageContext.request.contextPath}/base/stationAction_getAllStation.action',    
		    valueField:'stationSn',    
		    textField:'stationName',
		    panelHeight:300,
		    limitToList:true,
			onSelect:function(record){
				$('#dg').datagrid('load',{
					stationSn:record.stationSn
				});
			}  
		});
		
		//按钮权限
		if(resources.indexOf('040201')==-1){
			$('#add').css('display','none');
		}
		if(resources.indexOf('040202')==-1){
			$('#delete').css('display','none');
		}
		if(resources.indexOf('040203')==-1){
			$('#update').css('display','none');
		}
		if(resources.indexOf('040204')==-1){
			$('#import').css('display','none');
		}
		if(resources.indexOf('040205')==-1){
			$('#export').css('display','none');
		}
	})
		

</script>
</head>
<body style="margin:1px;">
	<input id="type" type="hidden" value="bike"/>
    <table id="dg"></table> 
    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>
    
    <!-- 新增页面 -->
    <div id="dd1" class="easyui-dialog" title="添加车辆" style="width:300px;height:200px;"   
        data-options="closed:true,collapsible:false,minimizable:false,maximizable:false,modal:true">   
   		<form id="ff1" method="post">   
		    <div style="margin: 15px;">   
		        <label for="bikeSn">车辆编号:</label>   
		        <input id="bikeSn1" class="easyui-textbox" type="text" name="bikeSn" data-options="prompt:'请使用四位纯数字编号',required:true,validType:'length[4]'" />   
		    </div>   
		    <div style="margin: 15px;">   
		        <label for="startDate">新增时间:</label>   
		        <input class="easyui-datebox" type="text" name="startDate" data-options="required:true" />   
		    </div>
		    <div style="margin-top: 25px;text-align:center">
		    	<a id="submit1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
		    	<a id="reset1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
		    </div>      
		</form>   
	</div>
	
	<!-- 更新页面 -->
	<div id="dd2" class="easyui-dialog" title="修改车辆" style="width:300px;height:200px;"   
        data-options="closed:true,collapsible:false,minimizable:false,maximizable:false,modal:true">   
   		<form id="ff2" method="post">   
		    <div style="margin: 15px;">   
		        <label for="bikeSn">车辆编号:</label>   
		        <input id="bikeSn2" class="easyui-textbox" type="text" name="bikeSn" data-options="prompt:'请使用四位纯数字编号',required:true,validType:'length[4]'" />   
		    </div>   
		    <div style="margin: 15px;">   
		        <label for="startDate">新增时间:</label>   
		        <input class="easyui-datebox" type="text" name="startDate" data-options="required:true" />   
		    </div>
		    <div style="margin-top: 25px;text-align:center">
		    	<a id="submit2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>  
		    </div>      
		</form>   
	</div>
</body>
</html>