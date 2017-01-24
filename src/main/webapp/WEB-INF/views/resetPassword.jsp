<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>Reset Password</title>
</head>

<body>
	<div class="login-box">
		<form action="resetPass" method="get">
			<div>
				<label for="email">E-Mail</label>
				<input type="text" id="email" name="email" placeholder="Enter email" required autofocus />
			</div>
			<div style="margin:5px">
				<input type="submit" value="Reset" />
			</div>
		</form>
		<a href="<c:url value='/login'/>">Login</a>
	</div>
</body>

</html>