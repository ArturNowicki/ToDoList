<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Form</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>

	<h2>User Registration</h2>

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
				<td colspan="3">
						<input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form:form>
	<br />
	<a href="<c:url value='userslist' />">Back</a>
</body>
</html>