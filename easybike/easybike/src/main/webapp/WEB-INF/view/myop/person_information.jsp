<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
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
		//修改信息
		$("#update").click(function(){
			$('#gg').css('display','none');
			$('#ff').css('display','');
		});
		//数据回显
		$("#gg").form('load','${pageContext.request.contextPath}/base/personAction_personInfor.action');
		//重置
		$("#reset").click(function(){
			$("#ff").form("reset");
		});
		//修改信息回显
		$("#ff").form('load','${pageContext.request.contextPath}/base/personAction_personInfor.action');
		//提交修改信息
		$('#submit').click(function(){
			$("#ff").form("enableValidation");
			if($('#ff').form('validate')){
				$('#ff').form('submit', {    
				    url:'${pageContext.request.contextPath}/base/personAction_updateInfor.action',      
				    success:function(data){    
				    	var result = eval('(' + data + ')');
				    	if(result.status=='ok'){
				    		parent.$.messager.alert("提示信息","修改成功！");
							$("#ff").form("reset");
							//关闭窗体
							parent.$("#win2").window("close");
							//刷新主页
							parent.$("#personName").html("您好，" + result.personName);
					   	}else{
					   		parent.$.messager.alert("提示信息","修改失败！",'error');
						}
				    }    
				});
			}
		})
	})
</script>
</head>
<body style="margin:1px;">
	<form id="gg" method="post">
		<div style="margin: 15px;">   
	        <label for="personSn">学&nbsp;&nbsp;号:</label>   
	        <input id="personSn" class="easyui-textbox" style="width:175px" type="text" name="personSn" disabled="disabled" data-options="position:'top',novalidate:'false'" />   
	    </div>   
	    <div style="margin: 15px;">   
	        <label for="personName">姓&nbsp;&nbsp;名:</label>   
	        <input class="easyui-textbox" type="text" name="personName" style="width:175px" disabled="disabled" data-options="required:true" />   
	    </div>
	    <div style="margin: 15px;">   
	        <label for="sex">性&nbsp;&nbsp;别:</label>   
	        <input class="easyui-combobox" name="sex" style="width:175px" disabled="disabled" data-options="
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
	        <input class="easyui-textbox" type="text" name="cellphoneNumber" style="width:175px" disabled="disabled" data-options="required:true,novalidate:'false'" />   
	    </div>
		<div id="before" style="margin-top: 25px;text-align:center">
			<a id="update" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
		</div>
	</form>
	<form id="ff" method="post" style="display:none;">   
	    <div style="margin: 15px;">   
	        <label for="personName">姓&nbsp;&nbsp;名:</label>   
	        <input id="personName" class="easyui-textbox" type="text" name="personName" style="width:175px" data-options="required:true" />   
	    </div>
	    <div style="margin: 15px;">  
	        <label for="sex">性&nbsp;&nbsp;别:</label>   
	        <input class="easyui-combobox" name="sex" style="width:175px" data-options="
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
	        <input class="easyui-textbox" type="text" name="cellphoneNumber" style="width:175px" data-options="required:true,validType:'length[11]'" />   
	    </div>
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
	    	<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
	    </div>      
	</form> 
</body>
</html>