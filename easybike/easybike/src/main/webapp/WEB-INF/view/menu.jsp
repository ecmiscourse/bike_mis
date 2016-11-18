<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main menu</title>	
</head>
<body>
	<ul id="menu">
	</ul>
	<script type="text/javascript">
		$(function() {
			$("#menu").tree(
							{
								url : '${pageContext.request.contextPath}/base/resourceAction_getMenu.action',
								onClick : function(node) {
									if ($('#menu').tree('isLeaf', node.target)) {//判断是否是叶子节点
										if($('#menu').tree('getParent', node.target)!=null)
											addNewTab(node.text,'${pageContext.request.contextPath}'+ node.url);
										else
											addNewTab(node.text,'${pageContext.request.contextPath}'+ node.url);
									}else{
										$("#menu").tree('toggle',node.target); 
									}
								}
							});
		});
	</script>
</body>
</html>