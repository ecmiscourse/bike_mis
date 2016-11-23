<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@include file="/public/head.jspf"%>
<script type="text/javascript">
$(function(){
   














	$("#submit").click(function(){
		$('#fm').form('submit',{
			url: "",						
			onSubmit:function(status){
				if(status!=null){
					$('#dlg').dialog('close');									
					$.messager.alert('成功','添加成功','info');
					$('#dg').datagrid("reload");
				}else{
					$.messager.alert('失败','添加失败','info');
					$('#dlg').dialog('close');
				}							
			}
		},"text");
	});




});
</script>
</head>
<body>
<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<div class="fitem">
				<label>账户:</label> <input name="username" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>密码:</label> <input name="password" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>邮箱:</label> <input name="email" class="easyui-validatebox"
					validType="email">
			</div>
			<div id="dlg-buttons">
				<a id="submit" href="#" class="easyui-linkbutton" iconCls="icon-ok">Save</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
			</div>
		</form>
	</div>
</body>
</html>