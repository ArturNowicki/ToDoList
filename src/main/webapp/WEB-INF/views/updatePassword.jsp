<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<script src="${pageContext.request.contextPath}/static/js/checkPass.js"></script>
<title>Reset Password</title>
</head>

<body>
	<div class="login-box">
		<c:if test="${error != null}">
			<p class="error">${error}</p>
		</c:if>

		<form:form method="POST" modelAttribute="passwordDto">
			<div>
				<form:password path="password" id="password"
					placeholder="Enter Password" maxlength="100" 
					onchange="checkPass(password, confirmPassword)" autofocus="autofocus" />
				<form:errors path="password" cssClass="error" />
			</div>
			<div>
				<form:password path="confirmPassword" id="confirmPassword"
					placeholder="Confirm Password" maxlength="100" 
					onchange="checkPass(password, confirmPassword)" />
				<form:errors path="confirmPassword" cssClass="error" />
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div style="margin: 5px">
				<input type="submit" value="Reset Password">
			</div>
		</form:form>

		<a href="<c:url value='login' />">Login</a>
	</div>

</body>
</html>