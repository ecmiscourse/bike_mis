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

		//提交 
		$('#submit').click(function(){
			$("#ff").form("enableValidation");
			if($('#ff').form('validate')){
				$('#ff').form('submit', {    
				    url:'${pageContext.request.contextPath}/circulate/lendAndReturnRecordAction_update2.action',     
				    queryParams:{'recordSn':row.recordSn,
				    	         'bikeSn':row.bikeSn
				    	},
				    success:function(data){    
				    	var result = eval('(' + data + ')');
				    	if(result.status=='ok'){
				    		parent.$.messager.alert("提示信息","修改成功！");
							$("#ff").form("reset");
							//关闭窗体
							parent.$("#win").window("close");
							//刷新dg
							parent.$("#dg").datagrid("reload");
					   	}else if(result.status=='zook'){
					   		parent.$.messager.alert("提示信息","不能重复还车！",'error');
					   	}else{
					   		parent.$.messager.alert("提示信息","修改失败！",'error');
						}
				    }    
				});
			}
		});
		
		//下拉框站点
		$('#cc').combobox({    
		    url:'${pageContext.request.contextPath}/circulate/lendAndReturnRecordAction_getAllStation2.action',    
		    valueField:'returnStationSn',    
		    textField:'returnStationName',
		    panelHeight:300,
		    limitToList:true,
			onSelect:function(record){
				$('#dg').datagrid('load',{
					returnStationSn:record.returnStationSn
				});
			}  
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	})
</script>
</head>
<body style="margin:1px;">
     <form id="ff" method="post">   
	    
	    
	    <div style="margin: 15px;">   
	        <label for="cellphoneNumber">备注&nbsp;&nbsp;:</label>   
	        <input class="easyui-textbox" type="textarea" name="returnMark" data-options="multiline:true"/>   
	    </div>
	     <div style="margin: 15px;">
	    <label >还车站点:</label>
	    <input id="cc" name="returnStationSn">	
	    </div>
	    

	    
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">还车</a>  
	    </div>      
	</form> 
</body>
</html>