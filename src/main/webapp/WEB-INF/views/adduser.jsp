<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Form</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>
	<%@include file="../jspf/menu.jspf" %>

	<h2>User Form</h2>

	<form:form method="POST" modelAttribute="user">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="login">Login: </label></td>
				<td><form:input path="login" id="login" /></td>
				<td><form:errors path="login" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="email">E-Mail: </label></td>
				<td><form:input path="email" id="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>

			<tr>
				<td colspan="1"><input type="submit" value="Save" /></td>
				<td colspan="1"><a href="userslist"><input type="button" value="Cancel"/></a></td>
			</tr>
		</table>
	</form:form>
</body>
</html>