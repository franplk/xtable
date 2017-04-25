<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
	var authUrl = "${ authLoginUrl }";
	
	if (!authUrl) {
		authUrl = "/sso/auth";
	}
	
	console.log("authUrl = " + authUrl);
	
	if (window != top) {
		top.location.href = authUrl;
	} else {
		window.location.href = authUrl;
	}
</script>

</head>
</html>