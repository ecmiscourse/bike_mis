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
		$('#tt').tree({    
		    url:'${pageContext.request.contextPath}/base/resourceAction_getResources.action',
		    queryParams:{roleSn:row.roleSn},
		    checkbox:true,
		    cascadeCheck:false,
		    lines:true,
		    onCheck:function(node,checked){
		    	if(checked==true){
		    		var node1=$('#tt').tree('getParent',node.target);
			    	if(node1!=null){
			    		$('#tt').tree('check', node1.target); 
			    	}
			    	$.ajax({
			    		url:'${pageContext.request.contextPath}/base/roleAction_addResource.action',
			    		method:'post',
			    		dataType:'json',
			    		data:{resourceSn:node.id,roleSn:row.roleSn},
			    		success:function(data){
			    			if(data.status=="nook"){
			    				$.messager.alert('我的消息','授权遇到未知错误！','error');
			    			}
			    			
			    		}
			    	})
		    	}else{
		    		var node2=$('#tt').tree('getChildren',node.target);
		    		for(var i=0;i<node2.length;i++){
		    			$('#tt').tree('uncheck', node2[i].target);
		    		}
		    		$.ajax({
			    		url:'${pageContext.request.contextPath}/base/roleAction_removeResource.action',
			    		method:'post',
			    		dataType:'json',
			    		data:{resourceSn:node.id,roleSn:row.roleSn},
			    		success:function(data){
			    			if(data.status=="nook"){
			    				$.messager.alert('我的消息','授权遇到未知错误！','error');
			    			}
			    		}
			    	})
		    	}		    	 
		    }
		});  
	})
</script>
</head>
<body style="margin:1px;">
    <ul id="tt" style="margin:10px 10px;"></ul> 
</body>
</html>