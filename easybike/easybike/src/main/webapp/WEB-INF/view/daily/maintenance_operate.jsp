<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<script type="text/javascript">
	$(function(){
		var row=parent.$('#dg').datagrid('getSelected');//父级dg选中行
	 //提交 		
		$('#submit').click(function(){
			$("#ff").form("enableValidation");
			if($('#ff').form('validate')){
				$('#ff').form('submit', {    
				    url:'${pageContext.request.contextPath}/daily/maintenanceAction_operate.action',       
				    queryParams:{'maintenanceSn':row.maintenanceSn},  
				    success:function(data){  
				    parent.$.messager.alert("提示信息","添加维修信息成功！");
					$("#ff").form("reset");
				    	//关闭窗体
				    	parent.$("#win").window("close");
				    	//刷新dg
						parent.$("#dg").datagrid("reload");
				    	}
				});
			}
		});		
		//重置
		$("#reset").click(function(){
			$("#ff").form("reset");
		})		
	})
</script>
</head>
<body style="margin:1px;">
    <form id="ff" method="post">    
	    <div style="margin: 15px;">   
	        <label for="repairMark">维修说明:</label>   
	        <input id="repairMark" class="easyui-textbox" name="repairMark" data-options="
	        	prompt:'在此输入备注，可不填！',
	        	multiline:true,
	        	height:66" />   
	    </div>   
	    <div style="margin: 15px;">   
	        <label for="repairDatetime">维修时间:</label>   
	        <input  id="repairDatetime"  name="repairDatetime" type= "text" class= "easyui-datebox" required ="required"> 
	    </div>
	 	<div style="margin: 15px;">
	 		<label for="radio">维修结果:</label>    
	  	   	<input type="radio" name="isrepairable"  value="1">完成</input>
	        <input type="radio" name="isrepairable"  value="0">报废</input>
	    </div>
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">完成</a>  
	    	<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
	    </div>      
	</form> 
</body>
</html>