<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>Login page</title>
</head>

<body>
	<div class="element">
		<c:url var="loginUrl" value="/login" />
		<form action="${loginUrl}" method="post">
			<c:if test="${param.error != null}">
				<p>Invalid username and password.</p>
			</c:if>
			<c:if test="${param.logout != null}">
				<p>You have been logged out successfully.</p>
			</c:if>
			<div>
				<label for="login"></label> <input type="text" id="login"
					name="login" placeholder="Enter Username" required>
			</div>
			<div>
				<label for="password"></label> <input type="password" id="password"
					name="password" placeholder="Enter Password" required>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<div class="form-actions">
				<input type="submit" value="Log in">
			</div>
		</form>
	</div>

</body>
</html>