<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<h2>Dashboard</h2>
	<table style="width: 100%">
		<tr>
			<td>NEW</td>
			<td>ACTIVE</td>
			<td>RESOLVED</td>
			<td>CLOSED</td>
		</tr>
		<c:forEach items="${item}" var="item">
			<c:choose>
				<c:when test="${item.type = NEW}">
					<tr>
						<td>ID: "${item.id}"</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:when>
				<c:when test="${item.type = ACTIVE}">
					<tr>
						<td></td>
						<td>ID: "${item.id}"</td>
						<td></td>
						<td></td>
					</tr>
				</c:when>
				<c:when test="${item.type = RESOLVED}">
					<tr>
						<td></td>
						<td></td>
						<td>ID: "${item.id}"</td>
						<td></td>
					</tr>
				</c:when>
				<c:when test="${item.type = CLOSED}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>ID: "${item.id}"</td>
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>
	</table>
</body>
</html>