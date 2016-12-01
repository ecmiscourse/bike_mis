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
		console.log(row);
		$('#cc1').combobox({    
		    url:'${pageContext.request.contextPath}/daily/distributeAction_queryAllStationExceptionself.action',    
		    queryParams:{stationSn:row.stationSn},
		    valueField:'stationSn',    
		    textField:'stationName',
		    prompt:'选择调出站点',
		    panelHeight:100,
		    onSelect:function(record){
		    	var url = '${pageContext.request.contextPath}/daily/distributeAction_queryAll.action?stationSn='+record.stationSn;    
		    	$('#cc2').combobox('clear');
		    	$('#cc2').combobox('reload', url);
		    }
		});
		$('#cc2').combobox({
			 valueField:'bikeSn',    
			 textField:'bikeSn',
			 panelHeight:100,
			 multiple:true,
			 prompt:'选择调出车辆，可多选',
		});
		//重置
		$("#reset").click(function(){
			$('#cc1').combobox('clear');
			$('#cc2').combobox('clear');
			//$("#ff").form("reset");
		});
		//提交 
		$('#submit').click(function(){
			
			$("#ff").form("enableValidation");
			if($('#ff').form('validate')){
				$('#ff').form('submit', {    
				    url:'${pageContext.request.contextPath}/daily/distributeAction_distribution.action',    
				    queryParams:{newstationSn:row.stationSn},
				    success:function(data){  
					    parent.$.messager.alert("提示信息","调拨车辆成功！");
						$("#ff").form("reset");
					    	//关闭窗体
					    	parent.$("#win").window("close");
					    	//刷新dg
							parent.$("#dg").datagrid("reload");
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
			    <input id="cc1" class="easyui-combobox" />   
				<input id="cc2" name="selectedbikes"  class="easyui-combobox" />  
	    </div>   
	        <div style="margin-top: 60px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>  
	    	<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
	    </div>
	  
	</form> 
</body>

</html>