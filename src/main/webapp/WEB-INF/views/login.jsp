<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>

<body>
	<div>
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