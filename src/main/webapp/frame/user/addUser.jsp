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
	<table width="280" border="0" align="center" cellpadding="3">
		<tr>
			<td width="80" align="right"><label for="userName">用户名：</label></td>
			<td width="200"><input type="text" name="userName" id="userName" /></td>
		</tr>
		<tr>
			<td align="right"><label for="password">密码：</label></td>
			<td><input type="password" name="password" id="password" /></td>
		</tr>
		<tr>
			<td align="right"><label for="name">姓名：</label></td>
			<td><input type="text" name="name" id="name" /></td>
		</tr>
		<tr>
			<td align="right"><label for="department">部门：</label></td>
			<td><input type="text" name="department" id="department" /></td>
		</tr>
	</table>
</div>

<div data-options="region:'south',split:true" style="height:50px;">
     <a class="easyui-linkbutton" id="btn_saveAndAdd" plain="true" icon="icon-ok" href="javascript:void(0)" > 保存并新增</a> 
     <a class="easyui-linkbutton" id="btn_save" plain="true" icon="icon-save" href="javascript:void(0)" > 保存</a> 
     <a class="easyui-linkbutton" id="btn_cancel" plain="true" icon="icon-cancel" href="javascript:void(0)" >取消</a>
</div>

</body>
<script type="text/javascript" src="/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh.js"></script>
</html>