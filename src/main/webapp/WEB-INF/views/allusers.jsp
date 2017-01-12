<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>List Users</title>
</head>
<body>
	<h4>${message}</h4>
	<h4>${error}</h4>
	<%@include file="../jspf/menu.jspf"%>
	<h2>List of Users</h2>
	<table>
		<tr>
			<td>ID</td>
			<td>NAME</td>
			<td>CONTACT</td>
		</tr>
		<c:forEach items="${users}" var="users">
			<tr>
				<td>${users.id}</td>
				<td>${users.login}</td>
				<td>${users.email}</td>
				<td>${users.userType}</td>
				<td><form action='edit-${users.id}-user' method="get">
						<input type="submit" value="Edit" />
					</form></td>
				<td><a href="delete-${users.id}-user" onclick="return confirm('Delete ${users.login}?')">
					<input type="submit" value="Delete" />
				</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value='/newuser' />">Add User</a>

</body>
</html>