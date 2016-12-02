<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	$("input",$("#studentId").next("span")).blur(function(){
		$('#studentId').textbox('enableValidation')
		if($('#studentId').textbox('isValid')){
			$.ajax({
				url:'${pageContext.request.contextPath}/circulate/lendAndReturnRecordAction_isExist.action',
				method:'POST',
				dataType:'json',
				data:{'studentId':$('#studentId').textbox('getValue')},
				success:function(data){
					if(data.isExist==true){
						parent.$.messager.alert('我的提示','该学号尚有车辆未还，无法借出！','warning');
						$('#studentId').textbox('clear');						
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
			    url:'${pageContext.request.contextPath}/circulate/lendAndReturnRecordAction_save.action',       
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
	//下拉框站点
	$('#cc').combobox({    
	    url:'${pageContext.request.contextPath}/base/stationAction_getAllStation.action',    
	    valueField:'stationSn',    
	    textField:'stationName',
	    queryParams:{stationSn:'stationSn'},
	    panelHeight:100,
	    limitToList:true,
		onSelect:function(record){
			var url = '${pageContext.request.contextPath}/daily/distributeAction_queryAll.action?stationSn='+record.stationSn;    
            $('#cc2').combobox('reload', url);
		}  
	});
	//下拉框自行车
	$('#cc2').combobox({      
	    valueField:'bikeSn',    
	    textField:'bikeSn',
	    panelHeight:100,
	    limitToList:true,
	    prompt:'请先选择站点'
	});
	
	
	
})
</script>
</head>
<body>
	<form id="ff" method="post">   
	    <div style="margin: 15px;">   
	        <label for="studentId">借车人学号:&nbsp;&nbsp;</label>   
	        <input id="studentId" class="easyui-textbox" type="text" name="studentId" data-options="required:true,validType:'length[8]'" />   
	    </div>   
	    <div style="margin: 15px;">   
	        <label for="studentName">借车人姓名:&nbsp;&nbsp;</label>   
	        <input class="easyui-textbox" type="text" name="studentName" data-options="required:true" />   
	    </div>
	    <div style="margin: 15px;">   
	        <label for="phoneNumber">借车人联系方式:</label>   
	        <input class="easyui-textbox" type="text" name="phoneNumber" data-options="required:true,validType:'length[11]'" />   
	    </div>
	    <div style="margin: 15px;">
	    	<label >借出站点:&nbsp;&nbsp;&nbsp;</label>
	    	<input id="cc" name="stationSn">	
	    </div>
	    <div style="margin: 15px;">
	    	<label >自行车编号:&nbsp;&nbsp;</label>
	    	<input id="cc2" name="bikeSn">	
	    </div>	    
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
	    	<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
	    </div>      
	</form> 	
</body>
</html>