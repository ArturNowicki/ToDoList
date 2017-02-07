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
<title>Add User</title>
</head>

<body>
	<%@include file="../jspf/menu.jspf"%>

	<div class="element-left">
		<h2>User Form</h2>

		<form:form method="POST" modelAttribute="newUserDto">
			<table>
				<tr>
					<td><label for="login">Login: </label></td>
					<td><form:input path="login" id="login"
							placeholder="Enter Username" autofocus="autofocus" maxlength="50" /></td>
					<td><form:errors path="login" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label for="password">Password: </label></td>
					<td><form:password path="password" id="password"
							placeholder="Enter Password" maxlength="100"
							onchange="checkPass(password, confirmPassword)" /></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label for="confirmPassword">Confirm Password: </label></td>
					<td><form:password path="confirmPassword" id="confirmPassword"
							placeholder="Confirm Password" maxlength="100"
							onchange="checkPass(password, confirmPassword)" /></td>
					<td><form:errors path="confirmPassword" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label for="email">E-Mail: </label></td>
					<td><form:input path="email" id="email"
							placeholder="Enter E-Mail" maxlength="50" /></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label>Type: </label></td>
					<td><form:select path="userType" id="type">
							<form:options />
						</form:select></td>
					<td><form:errors path="userType" cssClass="error" /></td>
				</tr>

				<tr>
					<td colspan="1"><input type="submit" value="Save" /></td>
					<td colspan="1"><a href="users"><input type="button"
							value="Cancel" /></a></td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>