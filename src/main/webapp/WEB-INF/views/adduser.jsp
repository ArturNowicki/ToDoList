<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Form</title>

<script>
	function check_pass() {
		if (password.value == confirm_password.value) {
			confirm_password.setCustomValidity("");
			} else {
			confirm_password.setCustomValidity("Passwords Don't Match");
		}
	}
</script>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>
	<%@include file="../jspf/menu.jspf"%>

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
				<td><label for="password">Password: </label></td>
				<td><form:password path="password" id="password"
						onchange='check_pass();' /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="confirm_password">Confirm Password: </label></td>
				<td><form:password path="password" id="confirm_password"
						onchange='check_pass();' /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="email">E-Mail: </label></td>
				<td><form:input path="email" id="email" /></td>
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
				<td colspan="1"><a href="users"><input type="button"
						value="Cancel" /></a></td>
			</tr>
		</table>
	</form:form>

	<a href="users">Change password</a>

</body>
</html>