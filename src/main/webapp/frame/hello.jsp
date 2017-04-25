<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎页</title>
<link rel="stylesheet" type="text/css" href="/assets/css/welcome.css" />
</head>
<body>
<table class="welcome">
	<tr>
		<td>欢迎进入报表系统！</td>
	</tr>
	<tr>
		<td id="nowTime"></td>
	</tr>
</table>
<div class="boundary">
	您的相关信息
</div>
<c:set var="user" value="${ sessionScope.auth_user }" scope="page"></c:set>
<table class="userinfo">
	<tr>
		<td class="key-user">登陆帐号：</td><td class="info-user">登录名</td>
		<td class="key-user">真实姓名：</td><td class="info-user">真实姓名</td>
	</tr>
	<tr>
		<td class="key-user">用户ID：</td><td class="info-user">${ user.userId }</td>
		<td class="key-user">您的角色：</td><td class="info-user">用户角色</td>
	</tr>
</table>
</body>
<script>
	//系统时间显示
	function sysTime() {
		var timeStr = new Date(<%= System.currentTimeMillis()%>).toLocaleString() + ' 星期'
				+ '日一二三四五六'.charAt(new Date().getDay());
		document.getElementById('nowTime').innerHTML = timeStr;
		setTimeout(sysTime, 1000);
	}
	sysTime();
</script>
</html>