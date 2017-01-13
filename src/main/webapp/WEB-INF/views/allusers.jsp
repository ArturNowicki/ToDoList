<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>List Users</title>
</head>

<body>

	<%@include file="../jspf/menu.jspf"%>

	<div class="element">
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
					<td><a href="delete-${users.id}-user"
						onclick="return confirm('Delete ${users.login}?')"> <input
							type="submit" value="Delete" />
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<div align="right" style="width: 80%">
		<a href="<c:url value='/newuser' />">Add User</a>
	</div>
	
	<div class="center error">
		<c:if test="${message != null}">
			<p>${message}</p>
		</c:if>
		<c:if test="${error != null}">
			<p>${error}</p>
		</c:if>
	</div>

</body>
</html>