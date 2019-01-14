<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/index.css" />
</head>
<body class="easyui-layout">
	<!-- Header Logo -->
	<div class="header" data-options="region:'north'" style="height:51px">
		<div class="logo"></div>
		<ul class="banner">
			<li><a href="javascript:void(0)" aim="http://www.emar.com">公司网站</a></li>
			<li><a href="javascript:void(0)" aim="/sso/logout">账号切换</a></li>
			<li><a href="javascript:void(0)" aim="/sso/logout">安全退出</a></li>
		</ul>
	</div>
	<!-- Left Menu -->
	<div data-options="region:'west'" style="width:150px;">
		<div class="easyui-accordion" data-options="fit:true,border:false,animate:false">
			<c:forEach var="pmenu" items="${ menuList }">
			<div title="${ pmenu.name }" data-options="iconCls:'icon-xreport'">
				<ul class="menu">
					<c:forEach var="cmenu" items="${ pmenu.subMenuList }">
					<li>
						<a href="javascript:void(0)" aim="${ cmenu.url }"><span class="icon-chart"></span>${ cmenu.name }</a>
					</li>
					</c:forEach>
				</ul>
			</div>
			</c:forEach>
		</div>
	</div>
	<!-- Center Main Page -->
	<div id="mainPage" data-options="region:'center'">
		<div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="${ entryPage.title }" closable="true" iconCls='icon-ok'>
				<iframe scrolling="auto" frameborder="0" src="${ entryPage.url }"></iframe>
			</div>
		</div>
	</div>
	<!-- Footer Copyright -->
	<div data-options="region:'south'" style="height:26px;">
		<div class="copyright">
			Copyright©版权所有，<a href="http://www.emar.com" target="_blank">北京亿玛在线科技有限公司</a>
		</div>
	</div>
</body>
<script type="text/javascript" src="/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh.js"></script>
<script type="text/javascript" src="/assets/js/index.js"></script>
</html>