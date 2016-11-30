<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<script type="text/javascript">
	$(function(){
		$("#ff").form("disableValidation");
		/*自定义验证*/
		$.extend($.fn.validatebox.defaults.rules, {    
		    type: {    
		        validator: function(value){
			        if(value.replace(/[^\w\/]/ig,'').length==value.length){
						return true;
				    }else{
				    	return false;   
					} 
		        },    
		        message: '数据非法 ！'   
		    }    
		}); 
		//ajax验证学号是否存在
		$("input",$("#roleSn").next("span")).blur(function(){
			$('#roleSn').textbox('enableValidation')
			if($('#roleSn').textbox('isValid')){
				$.ajax({
					url:'${pageContext.request.contextPath}/base/roleAction_isExist.action',
					method:'POST',
					dataType:'json',
					data:{'roleSn':$('#roleSn').textbox('getValue')},
					success:function(data){
						if(data.isExist==true){
							parent.$.messager.alert('我的提示','该编号已经存在！','warning');
							$('#roleSn').textbox('clear');						
						}
					}
				})
			}
		});
		//重置
		$("#reset").click(function(){
			$("#ff").form("reset");
		});
		//提交 
		$('#submit').click(function(){
			$("#ff").form("enableValidation");
			if($('#ff').form('validate')){
				$('#ff').form('submit', {    
				    url:'${pageContext.request.contextPath}/base/roleAction_save.action',       
				    success:function(data){
				    	$("#ff").form("disableValidation");    
				    	var result = eval('(' + data + ')');
				    	if(result.status=='ok'){
				    		parent.$.messager.alert("提示信息","添加成功！");
							$("#ff").form("reset");
							//关闭窗体
							parent.$("#win").window("close");
							//刷新dg
							parent.$("#dg").datagrid("reload");
					   	}else{
					   		parent.$.messager.alert("提示信息","添加失败！",'error');
						}
				    }    
				});
			}
		})
	})
</script>
</head>
<body style="margin:1px;">
    <form id="ff" method="post">   
	    <div style="margin: 15px;">   
	        <label for="roleSn">角色编号:</label>   
	        <input id="roleSn" class="easyui-textbox" type="text" name="roleSn" data-options="prompt:'只能输入字母或数字',required:true,validType:'type'" />   
	    </div>   
	    <div style="margin: 15px;">   
	        <label for="roleName">角色名称:</label>   
	        <input class="easyui-textbox" type="text" name="roleName" data-options="required:true" />   
	    </div>
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
	    	<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
	    </div>      
	</form> 
</body>
</html>