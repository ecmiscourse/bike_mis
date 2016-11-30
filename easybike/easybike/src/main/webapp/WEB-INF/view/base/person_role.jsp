<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>

<%@include file="/public/head.jspf"%>
<style>
	.f{
		text-align:center;
		margin-top:20px;
	}
</style>
<script type="text/javascript">
	var parentrow=parent.$('#dg').datagrid('getSelected');
	function remove(index){
		$('#dg').datagrid('selectRow',index);
		var row=$('#dg').datagrid('getSelected');
		if(row.personSn!='${personSn}'){
			$.messager.confirm('确认对话框', '您想要删除所选人员吗？', function(r){
				if (r){
				    $.ajax({
				    	url:'${pageContext.request.contextPath}/base/roleAction_removePerson.action',
				    	method:'post',
				    	dataType:'json',
				    	data:{personSn:row.personSn,roleSn:parentrow.roleSn},
				    	success:function(data){
				    		if(data.status=="ok"){
				    			parent.$.messager.alert('我的消息','删除成功！','info');
				    			//删除页面，刷新
								$("#dg").datagrid("clearSelections");
								$("#dg").datagrid("reload");
								//刷新主表
				 	        	parent.$('#dg').datagrid('reload');		
				    		}else{
				    			parent.$.messager.alert('我的消息','删除失败！','error');
				    		}
				    	}
				    })
				}
			});
		}else{
			$.messager.confirm('确认对话框', '删除自己，再次登录系统后，您将无法使用此角色拥有的权限，您确定要删除自己吗？', function(r){
				if (r){
					$.ajax({
				    	url:'${pageContext.request.contextPath}/base/roleAction_removePerson.action',
				    	method:'post',
				    	dataType:'json',
				    	data:{personSn:row.personSn,roleSn:parentrow.roleSn},
				    	success:function(data){
				    		if(data.status=="ok"){
				    			parent.$.messager.alert('我的消息','删除成功！','info');
				    			//删除页面，刷新
								$("#dg").datagrid("clearSelections");
								$("#dg").datagrid("reload");
								//刷新主表
								parent.$('#dg').datagrid('reload');
								
				    		}else{
				    			parent.$.messager.alert('我的消息','删除失败！','error');
				    		}
				    	}
				    })
				}
			});
		}
	}
	$(function(){
		var row=parent.$('#dg').datagrid('getSelected');//父级dg选中行
		$('#dg').datagrid({    
		    url:'${pageContext.request.contextPath}/base/personAction_queryByRoleSn.action',
			queryParams:{roleSn:row.roleSn},
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
			onLoadSuccess:function(){
				$('[name=btn]').linkbutton({    
				    iconCls: 'icon-remove',
				    plain:true
				});  
			},
			/*按钮*/
			toolbar:[{
				text:'<form id="ff" method="post"><input id="cc1" name="personSn"/></form>'
			},{
				id:'add',
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$('#ff').form('enableValidation');
					if($('#ff').form('validate')){
		 				$('#ff').form('submit',{    
		 				    url:"${pageContext.request.contextPath}/base/roleAction_addPerson.action", 
		 				    queryParams:{'roleSn':row.roleSn},      
		 				    success:function(data){
		 				    	var data = eval('(' + data + ')');      
		 				        if(data.status=="ok"){
		 				        	parent.$.messager.alert('我的消息','添加成功！','info');										
			 				    }else{
			 				    	parent.$.messager.alert('我的消息','添加失败！','error');
				 				}
		 				     	//删除页面，刷新
								$("#dg").datagrid("clearSelections");
								$("#dg").datagrid("reload");
								//刷新主表
				 	        	parent.$('#dg').datagrid('reload');
				 	        	$('#cc1').combogrid('clear');
				 	        	$('#ff').form('disableValidation'); 
		 				    }    
		 				});
			 		}
				}
			},{
				id:'export',
				text:'批量导入',
				iconCls:'icon-excel',
				handler:function(){
					$("#dd").dialog('open');
				}
			}],
		    columns:[[      
		        {field:'personSn',title:'学号',width:'40%',align:'center'},    
		        {field:'personName',title:'姓名',width:'40%',align:'center'},
		        {field:'remove',title:'删除',width:'20%',align:'center',formatter:function(value,row,index){
		        	return "<a name='btn' onclick='remove("+index+") 'href='#'></a>";
		        }}      
	    	]]    
		}); 
		$('#cc1').combogrid({ 
			url:'${pageContext.request.contextPath}/base/personAction_queryByQ.action',     		     
			idField:'personSn',    
		    textField:'personName',
		    width:350,
		    delay:500,
		    prompt:'输入学号号或姓名进行检索，选中后点击添加按钮进行添加',
		    mode: 'remote',
		    required:true,        
		    columns:[[    
		        {field:'personSn',title:'学号',width:'50%',align:'center'},    
		        {field:'personName',title:'姓名',width:'50%',align:'center'}
			]]     
		});
		$('#ff').form('disableValidation');
		
		//模板下载
		$('#download').click(function(){
			var form=$("<form>");
			form.attr("style","display:none");
			form.attr("target","");
			form.attr("method","post");
			form.attr("action","base/fileDownload?templateName=person_role.xlsx&&newFileName=人员授权模板.xlsx");
			$("body").append(form);
			form.submit();//提交表单
		});
		
		//自定义格式验证
		$.extend($.fn.validatebox.defaults.rules,{
			type:{
				validator:function(value,param){
					//获取文件扩展名
					//alert("value:" + value + ",param:" + param.length);
					var ext=value.substring(value.lastIndexOf(".")+1);
					var params=param[0].split(",");
					for(var i=0;i<params.length;i++){
						if(ext==params[i])
							return true;
					}
					return false;

				},
				//{0}代表传入的第一个参数
				message: '文件类型必须为:{0}'
			}
		});
		//文件框
		$('#fb').filebox({    
		    buttonText: '选择文件', 
		    buttonAlign: 'right',
		    validType:"type['xlsx']",
		    missingMessage:'请选择正确的文件(.xlsx)',
		    required:true 
		});
		$("#upload").form("disableValidation");
		
		$("#submit").click(function(){
			//开启验证
			$("#upload").form("enableValidation");
			if($("#upload").form("validate")){
				//ajax提交
				$('#submit').linkbutton('disable');

				$('#upload').form('submit',{
					url:'${pageContext.request.contextPath}/base/personAction_personRole.action',
					queryParams:{roleSn:row.roleSn},
					success:function(result){
						$('#submit').linkbutton('enable');
						var data = eval('(' + result + ')');  
						if(data.errorNum==0){
							parent.$.messager.alert('我的消息','导入成功！','info');
						}else{
							parent.$.messager.alert('我的消息',data.message,'warning');
						}
						$("#dd").dialog('close');
						$("#dg").datagrid("load");
						parent.$("#dg").datagrid("load");
						$("#upload").form("disableValidation");
						$("#upload").form("reset");
					}
				});
			}
		});
	})
		

</script>
</head>
<body style="margin:1px;">
    <table id="dg"></table> 
    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>
    <!-- 批量导入 -->
    <div id="dd" class="easyui-dialog" title="批量导入" style="width:310px;height:150px;"   
        data-options="resizable:true,modal:true,closed:true">   
		 	<!--模板导入  -->
		 	<div id="form">
				 <form id="upload" method='post' enctype='multipart/form-data'>
				 	<div class="f">
				 		<label for="fb">浏览:</label>
				 		<input id='fb' type='text' name='uploadExcel' style='width:200px'/>
				 	</div>
				 </form>
				 <!-- 模板下载 --> 	        
				 <div class="f">
				    <a  href="#" id="download" style="text-decoration: none">没有模板？点我下载！</a>
				    <a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-import'">导入</a>   
				</div> 
			</div>  
	</div>
     
</body>
</html>