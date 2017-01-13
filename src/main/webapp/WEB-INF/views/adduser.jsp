<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>Add User</title>
<script type="text/javascript" src="/static/js/check_pass.js">
/* 	function check_pass(pass, confirm_pass) {
		if (pass.value == confirm_pass.value) {
			confirm_pass.setCustomValidity("");
		} else {
			confirm_pass.setCustomValidity("Passwords Don't Match");
		}
	} */
</script>

</head>

<body>
	<%@include file="../jspf/menu.jspf"%>

	<div class="element">
		<h2>User Form</h2>

		<form:form method="POST" modelAttribute="user">
			<table>
				<tr>
					<td><label for="login">Login: </label></td>
					<td><form:input path="login" id="login" placeholder="Enter Username"/></td>
					<td><form:errors path="login" cssClass="error"/></td>
				</tr>

				<tr>
					<td><label for="password">Password: </label></td>
					<td><form:password path="password" id="password" placeholder="Enter Password"
							onchange='check_pass(password, confirm_password);' /></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label for="confirm_password">Confirm Password: </label></td>
					<td><input type="password" id="confirm_password" placeholder="Confirm Password"
							onchange='check_pass(password, confirm_password)' /></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label for="email">E-Mail: </label></td>
					<td><form:input path="email" id="email" placeholder="Enter E-Mail"/></td>
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