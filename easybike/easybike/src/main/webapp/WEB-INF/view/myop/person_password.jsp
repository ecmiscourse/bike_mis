<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/public/head.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/md5.js"></script>

<script type="text/javascript">
	$(function(){
		/*自定义验证*/
		$.extend($.fn.validatebox.defaults.rules, {
		    length: {    
		        validator: function(value, param){
			        if(value.length >= param[0] && value.length<=param[1]){
						return true;
				    }else{
				    	return false;   
					} 
		        },
		        message: '请输入6~15位字符！'   
		    },
		    equals: {    
		        validator: function(value,param){    
		            return value == $(param[0]).val();    
		        },    
		        message: '两次密码不同！'   
		    } 
		}); 
		//ajax验证密码是否正确
		$("input",$("#password1").next("span")).blur(function(){
			$('#password1').textbox('enableValidation')
			if($('#password1').textbox('isValid')){
				$.ajax({
					url:'${pageContext.request.contextPath}/base/personAction_isPassword.action',
					method:'POST',
					dataType:'json',
					data:{'password':MD5($('#password1').textbox('getValue'))},
					success:function(data){
						if(data.status=="nook"){
							parent.$.messager.alert('我的提示','密码错误！','warning');
							$('#password1').textbox('clear');						
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
				    url:'${pageContext.request.contextPath}/base/personAction_updatePassword.action',
				    queryParams:{password:MD5($('#password2').val())},
				    success:function(data){
				    	$("#ff").form("disableValidation");    
				    	var result = eval('(' + data + ')');
				    	if(result.status=='ok'){
				    		parent.$.messager.alert("提示信息","修改密码成功！");
							$("#ff").form("reset");
							//关闭窗体
							parent.$("#win1").window("close");
							//刷新dg
							parent.$("#dg").datagrid("reload");
					   	}else{
					   		parent.$.messager.alert("提示信息","修改密码成功！",'error');
						}
				    }    
				});
			}
		})
	})
</script>
<title>修改密码</title>
</head>
<body style="margin:1px;">
	<form id="ff" method="post">
		<div style="margin:15px;">
			<label>原密码:&nbsp;</label>
			<input id="password1" class="easyui-passwordbox" data-options="position:'top',required:true,validType:'length[6,15]'"/>
		</div>
		<div style="margin:15px;">
			<label>新密码:&nbsp;</label>
			<input id="password2" class="easyui-passwordbox" data-options="position:'top',required:true,validType:'length[6,15]'"/>
		</div>
		<div style="margin:15px;">
			<label>确认密码:</label>
			<input id="rpassword" class="easyui-passwordbox" validType="equals['#password2']" data-options="required:true"/>
		</div>
		<div style="margin-top: 25px;text-align:center">
	    	<a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>  
	    	<a id="reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>  
	    </div>
	
	</form>
	
</body>
</html>