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
		//提交 
		$('#submit').click(function(){
			$("#ff").form("enableValidation");
			if($('#ff').form('validate')){
				$('#ff').form('submit', {    
				    url:'${pageContext.request.contextPath}/circulate/lendAndReturnRecordAction_update2.action',     
				    queryParams:{
				    	'recordSn':row.recordSn,
				        'bikeSn':row.bikeSn
				    },
				    success:function(data){    
				    	var result = eval('(' + data + ')');
				    	if(result.status=='ok'){
				    		parent.$.messager.alert("提示信息","还车成功！");
							$("#ff").form("reset");
							//关闭窗体
							parent.$("#win").window("close");
							//刷新dg
							parent.$("#dg").datagrid("reload");
					   	}else{
					   		parent.$.messager.alert("提示信息","还车失败！",'error');
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
		    panelHeight:100,
		    required:true,
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
	        <label for="returnMark">备&nbsp;&nbsp;注:</label>   
	        <input class="easyui-textbox" name="returnMark" data-options="multiline:true,height:44,prompt:'可不填！'"/>   
	    </div>
	     <div style="margin: 15px;">
	    <label >还车站点:</label>
	    <input id="cc" name="returnStationSn">	
	    </div>
	    <div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">还车</a>  
	    </div>      
	</form> 
</body>
</html>