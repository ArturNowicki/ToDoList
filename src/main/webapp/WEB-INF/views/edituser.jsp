<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>Edit User</title>
</head>

<body>
	<%@include file="../jspf/menu.jspf"%>

	<div class="element-left">
		<h2>Edit User</h2>

		<form:form method="POST" modelAttribute="editUserDto">
			<form:input type="hidden" path="id" id="id" />
			<form:input type="hidden" path="login" id="login" />
			<table>
				<tr>
					<td><label>Login: </label></td>
					<td><label>${editUserDto.login}</label></td>
				</tr>

				<tr>
					<td><label for="email">E-Mail: </label></td>
					<td><form:input path="email" id="email" autofocus="autofocus"
							maxlength="50" /></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label>Type: </label></td>
					<td><form:select path="userType" id="type">
							<form:options />
						</form:select></td>
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