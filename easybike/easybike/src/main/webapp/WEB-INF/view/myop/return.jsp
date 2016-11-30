<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我归还的</title>
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
		        {field:'bikeSn',title:'自行车编号',width:'13%',align:'center'},
		        {field:'studentId',title:'借车人学号',width:'13%',align:'center'},
		        {field:'studentName',title:'借车人姓名',width:'13%',align:'center'},
		        {field:'phoneNumber',title:'借车人联系方式',width:'14%',align:'center'},
		        {field:'lendPerson',title:'借出人',width:'13%',align:'center'},
		        {field:'lendDateTime',title:'借出时间',width:'14%',align:'center'},
		        {field:'returnDateTime',title:'归还时间',width:'14%',align:'center'}
		    ]],
		    toolbar:[{
				text:'<input id="search" type="text" style="width:300px">'
			}]
		});
			$('#search').textbox({    
			    buttonText:'搜索',    
			    iconCls:'icon-search', 
			    iconAlign:'left',
			    prompt: '请输入自行车编号进行查询',
			    onClickButton:function(){
				   $('#box').datagrid('reload',{
				    	bikeSn:$('#search').textbox('getValue')
				   })
				}
			})

	})
		
	</script>	
</head>
<body>
	<table id="box"></table>
	
</body>
</html>