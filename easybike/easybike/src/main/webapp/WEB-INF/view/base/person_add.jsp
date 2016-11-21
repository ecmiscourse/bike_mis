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
		//ajax验证学号是否存在
		$("input",$("#personSn").next("span")).blur(function(){
			$('#personSn').textbox('enableValidation')
			if($('#personSn').textbox('isValid')){
				$.ajax({
					url:'${pageContext.request.contextPath}/base/personAction_isExist.action',
					method:'POST',
					dataType:'json',
					data:{'personSn':$('#personSn').textbox('getValue')},
					success:function(data){
						if(data.isExist==true){
							parent.$.messager.alert('我的提示','该学号已经存在！','warning');
							$('#personSn').textbox('clear');						
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
				    url:'${pageContext.request.contextPath}/base/personAction_save.action',       
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
	        <label for="personSn">学&nbsp;&nbsp;号:</label>   
	        <input id="personSn" class="easyui-textbox" type="text" name="personSn" data-options="position:'top',required:true,validType:'length[8]'" />   
	    </div>   
	    <div style="margin: 15px;">   
	        <label for="personName">姓&nbsp;&nbsp;名:</label>   
	        <input class="easyui-textbox" type="text" name="personName" data-options="required:true" />   
	    </div>
	    <div style="margin: 15px;">   
	        <label for="sex">性&nbsp;&nbsp;别:</label>   
	        <input class="easyui-combobox" name="sex" data-options="
				valueField: 'label',
				required:true,
				textField: 'value',
				panelHeight:'auto',
				data: [{
					label: '0',
					value: '男'
				},{
					label: '1',
					value: '女'
				}]" /> 
	    </div>
	    <div style="margin: 15px;">   
	        <label for="cellphoneNumber">联系方式:</label>   
	        <input class="easyui-textbox" type="text" name="cellphoneNumber" data-options="required:true,validType:'length[11]'" />   
	    </div>
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
	    	<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
	    </div>      
	</form> 
</body>
</html>