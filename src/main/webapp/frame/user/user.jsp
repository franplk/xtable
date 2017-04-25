<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="/easyui/themes/easyui.css" />
	<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css" />
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="userTable" style="width:100%;height:500px"></table>
	</div>
	<div id="userAddDialog" style="width:600px;height:500px"></div>
</body>
<script type="text/javascript" src="/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh.js"></script>
<script type="text/javascript">
$(function() {
	// 初始化添加用户弹出框
	$("#userAddDialog").dialog({
		title:"添加用户",
		closed:true,modal:true,
		href:"/user/page/add"
	});
	// 初始化 EasyUI DataGrid
	$('#userTable').datagrid({
		title:'用户列表',
		fit:true,rownumbers:true,
		striped:true,showHeader:true,
		/* pagination:true, pageSize:20,pageList:[10,20,30,50,100], */
		columns:[[
			{field:'id',title:'ID',width:100},
			{field:'username',title:'用户名',width:100},
			{field:'chn_name',title:'真实姓名',width:100},
			{field:'email',title:'邮箱',width:100},
			{field:'company',title:'所属部门',width:100},
			{field:'regDate',title:'注册时间',width:100},
			{field:'expireDate',title:'过期时间',width:100},
			{field:'status',title:'状态',width:100},
		]],
	    toolbar: [{
	    	text:"添加用户",iconCls:'icon-add',
			handler:function(){
				$("#userAddDialog").dialog("open");
			}
		},'-',{
			text:"删除用户",iconCls: 'icon-cancel',
			handler:function(){
				var userRow = $('#userTable').datagrid('getChecked');
				if (userRow.length == 0) {
					$.messager.alert('警告','请选择一个用户!','warning');
				} else {
					alert("删除用户");
				}
			}
		},'-',{
			text:"修改用户",iconCls: 'icon-pencil',
			handler:function() {
				var userRow = $('#userTable').datagrid('getChecked');
				if (userRow.length == 0) {
					$.messager.alert('警告','请选择一个用户!','warning');
				} else {
					alert("修改用户");
				}
			}
		}],
	});
	
	function init () {
		$("#userTable").datagrid('loading');
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"/user/data/list",
			success:function(result) {
				if (result.msg) {
					$.messager.alert('ERROR',result.msg,'error');
					return;
				}
				// 加载表格数据
				$("#userTable").datagrid('loadData', result['tableData']);
			},error:function(result) {
				$.messager.alert('ERROR','查询失败,请联系管理员','error');
			},complete:function(){
				$("#userTable").datagrid('loaded');
			}
		});
	}
	
	// init
	init();
});
</script>
</html>