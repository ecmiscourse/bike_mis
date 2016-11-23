<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>

<%@include file="/public/head.jspf"%>
<style>
	.f{
		text-align:center;
		margin-top:20px;
	}
</style>
<script type="text/javascript">
	var timerId;
	function getForm(){
	    //使用JQuery从后台获取JSON格式的数据
	    $.ajax({
	       type:"post",//请求方式
	       url:"base/personAction_importSession.action",//发送请求地址
	       timeout:30000,//超时时间：30秒
	       dataType:"json",//设置返回数据的格式
	       //请求成功后的回调函数 data为json格式
	       success:function(data){
	          if(data.value>=100){
	             clearInterval(timerId);
	          }
	          $('#p').progressbar('setValue',data.value);
	      },
	      //请求出错的处理
	      error:function(){
	         clearInterval(timerId);
	      }
	   });
	}
	$(function(){
		console.log(parent.$('#type').val());
		var url1="";
		var url2="";
		if(parent.$('#type').val()=='person'){
			url1="base/personAction_importPerson";
			url2="base/fileDownload?templateName=person.xlsx&&newFileName=人员导入模板.xlsx";
		}else if(parent.$('#type').val()=='bike'){
			url1="base/bikeAction_importBike";
			url2="base/fileDownload?templateName=bike.xlsx&&newFileName=车辆导入模板.xlsx";
		}
		//模板下载
		$('#download').click(function(){
			var form=$("<form>");
			form.attr("style","display:none");
			form.attr("target","");
			form.attr("method","post");
			form.attr("action",url2);
			$("body").append(form);
			form.submit();//提交表单
		});
		
		//自定义格式验证
		$.extend($.fn.validatebox.defaults.rules,{
			type:{
				validator:function(value,param){
					//获取文件扩展名
					//alert("value:" + value + ",param:" + param.length);
					var ext=value.substring(value.lastIndexOf(".")+1);
					var params=param[0].split(",");
					for(var i=0;i<params.length;i++){
						if(ext==params[i])
							return true;
					}
					return false;

				},
				//{0}代表传入的第一个参数
				message: '文件类型必须为:{0}'
			}
		});
		//文件框
		$('#fb').filebox({    
		    buttonText: '选择文件', 
		    buttonAlign: 'right',
		    validType:"type['xlsx']",
		    missingMessage:'请选择正确的文件(.xlsx)',
		    required:true 
		});

		$("#upload").form("disableValidation");
		$("#submit").click(function(){
			//开启验证
			$("#upload").form("enableValidation");
			if($("#upload").form("validate")){
				//按钮和框隐藏
				$('#form').css('display','none');				
				$('#p').css('margin-top','35px');
				$('#p').css('display','');
				timerId=setInterval(getForm,500);
				//ajax提交
				$('#upload').form('submit',{
					url:url1,
					success:function(result){
						var data = eval('(' + result + ')');  
						if(data.errorNum==0){
							parent.$.messager.alert('我的消息','导入成功！','info');
						}else{
							parent.$.messager.alert('我的消息',data.message,'warning');
						}
						clearInterval(timerId);
						parent.$("#win").window('close');
						parent.$("#dg").datagrid("reload");
						$("#upload").form("disableValidation");
						$("#upload").form("reset");
						
					}
				});
			}
		});
	})
</script>
</head>
<body style="margin:1px;">
    <div id="form">
	 <!--模板导入  -->
	 <form id="upload" method='post' enctype='multipart/form-data'>
	 	<div class="f">
	 		<label for="fb">浏览:</label>
	 		<input id='fb' type='text' name='uploadExcel' style='width:200px'/>
	 	</div>
	 </form>
	 <!-- 模板下载 --> 	        
	 <div class="f">
	    <a  href="#" id="download" style="text-decoration: none">没有模板？点我下载！</a>
	    <a id="submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-import'">导入</a>   
	 </div>
	 </div>
	 <div id="p" class="easyui-progressbar" style="width:300px;margin-left:20px;display:none"></div>  
</body>
</html>