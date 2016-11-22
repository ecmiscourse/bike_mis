<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<script type="text/javascript">
	$(function(){
		var row=parent.$('#dg').datagrid('getSelected');	
		$("#ff").form("disableValidation");
		//数据回显
		$('#ff').form('load',{
			stationSn:row.stationSn,
			stationName:row.stationName,
			location:row.location
		})
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
		$("input",$("#stationSn").next("span")).blur(function(){
			$('#stationSn').textbox('enableValidation')
			if($('#stationSn').textbox('isValid')&&$('#stationSn').textbox('getValue')!=row.stationSn){
				$.ajax({
					url:'${pageContext.request.contextPath}/base/stationAction_isExist.action',
					method:'POST',
					dataType:'json',
					data:{'stationSn':$('#stationSn').textbox('getValue')},
					success:function(data){
						if(data.isExist==true){
							parent.$.messager.alert('我的提示','该编号已经存在！','warning');
							$('#stationSn').textbox('clear');						
						}
					}
				})
			}
		});
		//提交 
		$('#submit').click(function(){
			$("#ff").form("enableValidation");
			if($('#ff').form('validate')){
				$('#ff').form('submit', {    
				    url:'${pageContext.request.contextPath}/base/stationAction_update.action',       
				    queryParams:{'oldStationSn':row.stationSn},  
				    success:function(data){
				    	$("#ff").form("disableValidation");    
				    	var result = eval('(' + data + ')');
				    	if(result.status=='ok'){
				    		parent.$.messager.alert("提示信息","修改成功！");
							$("#ff").form("reset");
							//关闭窗体
							parent.$("#win").window("close");
							//刷新dg
							parent.$("#dg").datagrid("reload");
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
    <form id="ff" method="post">   
	    <div style="margin: 15px;">   
	        <label for="stationSn">站点编号:</label>   
	        <input id="stationSn" class="easyui-textbox" type="text" name="stationSn" data-options="prompt:'请输入四位纯数字编号',required:true,validType:'length[4]'" />   
	    </div>   
	    <div style="margin: 15px;">   
	        <label for="stationName">站点名称:</label>   
	        <input class="easyui-textbox" type="text" name="stationName" data-options="required:true" />   
	    </div>
	    <div style="margin: 15px;">   
	        <label for="location">站点位置:</label>   
	        <input class="easyui-textbox" name="location" style="height:44px;" data-options="multiline:true"/> 
	    </div>
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>  
	    </div>      
	</form> 
</body>
</html>