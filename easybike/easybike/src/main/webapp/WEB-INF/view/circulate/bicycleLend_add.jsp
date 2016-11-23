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
   




	










	




});
</script>
</head>
<body>

		 <form id="ff" method="post">   
	    <div style="margin: 15px;">   
	        <label for="personSn">借车人学号:&nbsp;&nbsp;</label>   
	        <input id="personSn" class="easyui-textbox" type="text" name="studentId" data-options="position:'top',required:true,validType:'length[8]'" />   
	    </div>   
	    <div style="margin: 15px;">   
	        <label for="personName">借车人姓名:&nbsp;&nbsp;</label>   
	        <input class="easyui-textbox" type="text" name="studentName" data-options="required:true" />   
	    </div>
	    <div style="margin: 15px;">   
	        <label for="cellphoneNumber">借车人联系方式:</label>   
	        <input class="easyui-textbox" type="text" name="phoneNumber" data-options="required:true,validType:'length[11]'" />   
	    </div>
	    <div style="margin: 15px;">   
	        <label for="sex">站点:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>   
	        <input class="easyui-combobox" name="lendStation" data-options="
				valueField: 'label',
				required:true,
				textField: 'value',
				panelHeight:'auto',
				data: [{
					label: '0',
					value: '站点X'
				},{
					label: '1',
					value: '站点Y'
				},
				{
					label: '2',
					value: '站点Z'
				}]" /> 
	    </div>
	     <div style="margin: 15px;">   
	        <label for="sex">自行车:&nbsp;&nbsp;&nbsp;&nbsp;</label>   
	        <input class="easyui-combobox" name="bike" data-options="
				valueField: 'label',
				required:true,
				textField: 'value',
				panelHeight:'auto',
				data: [{
					label: '0',
					value: '自行车0'
				},{
					label: '1',
					value: '自行车1'
				},
				{
					label: '2',
					value: '自行车2'
				}]" /> 
	    </div>
	    
	    
	    
	    
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
	    	<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
	    </div>      
	</form> 
	
</body>
</html>