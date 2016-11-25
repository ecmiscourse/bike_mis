<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园公共自行车管理系统</title>
<%@include file="/public/head.jspf"%>
<script type="text/javascript">

$(function(){
	$('#dg').datagrid({    
	    url:'${pageContext.request.contextPath}/daily/traceAction_queryAllTrace.action',
	    fitColumns:true,
	    fit:true,
	    striped:true,/*斑马线*/
	    nowarp:true,/*数据同一行*/ 
	    loadmsg:'请等待',
	    rownumbers:true,
	    singleSelect:true,
		pagination:true,
		pageNumber:1,
		pageSize:15,
		pageList:[15,30,50,100], 
		columns:[[
		          {field:'recordSn',title:'记录编号',width:'10%',align:'center'},
		          {field:'studentId',title:'借车人学号',width:'10%',align:'center'},
		          {field:'lendDateTime',title:'借出时间',width:'10%',align:'center'},
		          {field:'returnDateTime',title:'归还时间',width:'10%',align:'center'},
		          {field:'isHasReturned',title:'是否归回',width:'10%',align:'center'},
		          {field:'lendPerson',title:'借出人',width:'10%',align:'center'},
		          {field:'lendStationSn',title:'借出站点',width:'10%',align:'center'},
		          {field:'returnPerson',title:'操作归还的人',width:'10%',align:'center'},
    			  {field:'returnStationSn',title:'归还站点',width:'10%',align:'center'} 
			]]
		});
	//提交 
	$('#tb').textbox({    
	    buttonText:'Search',    
	    iconCls:'icon-man', 
	    iconAlign:'left' ,
	    onClickButton:function(){
	    	$('#dg').datagrid('reload',{
	    		bikeSn:$('#tb').textbox('getValue')
	    	})
	    }
	})

})

</script>
</head>
<body style="margin:1px;">
 <div style="margin-top: 25px;text-align:center">
	<input id="tb" type="text" style="width:300px">
  </div>
	<table id="dg"></table> 
</body>

</html>