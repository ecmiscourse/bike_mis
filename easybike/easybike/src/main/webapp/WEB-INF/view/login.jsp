<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo.png" type="image/x-icon">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/md5.js"></script>
<style type="text/css">
	body{ background:#0066A8;}
	.tit{ margin:auto; margin-top:170px; text-align:center; width:350px; padding-bottom:20px;}
	.login-wrap{ width:220px; padding:30px 50px 0 330px; height:220px; background:#fff url(${pageContext.request.contextPath}/images/20150212154319.jpg) no-repeat 30px 40px; margin:auto; overflow: hidden;}
	.copyright{ margin:auto; margin-top:10px; text-align:center; width:370px; color:#CCC}
	@media (max-height: 700px) {.tit{ margin:auto; margin-top:100px; }}
	@media (max-height: 500px) {.tit{ margin:auto; margin-top:50px; }}
</style>

<script type="text/javascript">
	$(function(){
		$('#ff').form('disableValidation');
		$('#submit').click(function(){
			$('#ff').form('enableValidation');
			if($('#ff').form('validate')){
				$('#ff').form('submit', {    
				    url:'${pageContext.request.contextPath}/base/personAction_login', 
				    queryParams:{password:MD5($("#password").val())},      
				    success:function(data){    
				    	var data = eval('(' + data + ')');  // change the JSON string to javascript object    
				        if (data.status=='ok'){   
				        	location.href ="${pageContext.request.contextPath}/main";   
				        }else{
				        	$.messager.alert('警告','用户名密码不正确！');
					    }   
				    }    
				}); 
			}
		})
	})

</script>
</head>
<body>
<form id="ff" method="post">
	<div class="tit"><img src="${pageContext.request.contextPath}/images/tit.png" alt="" /></div>
		<div class="login-wrap">
		  <table width="300px" border="0" cellspacing="0" cellpadding="0">		    
		    <tr height="80px">
		    	<td style="width:65px"><span>用户名：</span></td>
		      	<td><input name="personSn" class="easyui-textbox" data-options="iconCls:'icon-man',required:true" style="width:180px"></td>
		    </tr>
		    <tr>
		    	<td><span>密  码：</span></td>
		      	<td><input id="password" class="easyui-passwordbox" prompt="密码" required="true" iconWidth="24" style="width:180px;height:24px;padding:10px"></td>
		    </tr>
		    <tr height="80px">
		    	<td colspan="2" style="text-align: center"><a href="#" id="submit" class="easyui-linkbutton">&nbsp;登 &nbsp;录&nbsp;</a></td>
		    </tr>
		  </table>
		</div>	
	<div class="copyright">建议使用IE8以上版本或谷歌浏览器</div>
</form>
</body>
</html>