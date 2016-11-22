<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我借出的</title>
	<%@include file="/public/head.jspf"%>
	<script type="text/javascript">
	$(function(){
		$('#box').datagrid({    
		    url:'${pageContext.request.contextPath}/myop/lendAndReturnRecordAction_queryByPersonSn.action',
		    fitColumns:true,
		    fit:true,
		    striped:true,/*斑马线*/
		    nowarp:true,/*数据同一行*/ 
		    loadmsg:'请等待',
		    rownumbers:true,
		    singleSelect:true,
		    rownumbers:true,
		    /*分页*/
			pagination:true,
			pageNumber:1,
			pageSize:15,
			pageList:[15,30,50,100], 
		    columns:[[
				{field:'recordSn',title:'借车记录编号',width:'11%',align:'center'},
		        {field:'bikeSn',title:'自行车编号',width:'11%',align:'center'},
		        {field:'studentId',title:'借车人学号',width:'11%',align:'center'},
		        {field:'studentName',title:'借车人姓名',width:'11%',align:'center'},
		        {field:'phoneNumber',title:'借车人联系方式',width:'11%',align:'center'},
		        {field:'lendDateTime',title:'借出时间',width:'11%',align:'center'},
		        {field:'isHasReturned',title:'是否归还',width:'11%',align:'center'},
		        {field:'returnPerson',title:'操作归还的人',width:'11%',align:'center'},
		        {field:'returnDateTime',title:'归还时间',width:'11%',align:'center'}
		    ]],
		    toolbar:[{
				text:'<input id="search" type="text" style="width:300px">'
			}]
		});
			$('#search').textbox({    
			    buttonText:'搜索',    
			    iconCls:'icon-search', 
			    iconAlign:'left'       
			})

	})
		
	</script>	
</head>
<body>
	<table id="box"></table>
	<!-- <div id="search">
		<div>
			输入自行车编号：<input type="text" name="" >
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div> -->
</body>
</html>