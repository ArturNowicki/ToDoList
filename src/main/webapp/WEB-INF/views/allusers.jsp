<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Users</title>
</head>
<body>
	<h2>List of Users</h2>
	<table>
		<tr>
			<td>ID</td>
			<td>NAME</td>
			<td></td>
		</tr>
		<c:forEach items="${user}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.login}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>