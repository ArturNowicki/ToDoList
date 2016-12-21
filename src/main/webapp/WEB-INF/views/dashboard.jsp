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
	<table cellspacing="2" cellpadding="3" border="1" align="center" style="width: 60%">
 		<col width="25%">
 		<col width="25%">
 		<col width="25%">
 		<col width="25%">
 		<tr>
			<td>NEW</td>
			<td>ACTIVE</td>
			<td>RESOLVED</td>
			<td>CLOSED</td>
		</tr>
		<c:forEach items="${item}" var="item">
			<c:choose>
				<c:when test="${item.state == 'NEW'}">
					<tr>
						<a href="<c:url value='/new' />">Add New Employee</a>
					
						<td><a href="<c:url value='/item-${item.id}' /> ">
							<div>
								<p>ID: "${item.id}"</p>
								<p>Title: "${item.title}"</p>
								<p>Login: "${item.assignedUser.login}"</p>
								<p>Type: "${item.type}</p>
							</div>
							</a>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:when>
				<c:when test="${item.state == 'ACTIVE'}">
					<tr>
						<td></td>
						<td>
							<div>
								<p>ID: "${item.id}"</p>
								<p>Title: "${item.title}"</p>
								<p>Login: "${item.assignedUser.login}"</p>
								<p>Type: "${item.type}</p>
							</div>
						</td>
						<td></td>
						<td></td>
					</tr>
				</c:when>
				<c:when test="${item.state == 'RESOLVED'}">
					<tr>
						<td></td>
						<td></td>
						<td>
							<div>
								<p>ID: "${item.id}"</p>
								<p>Title: "${item.title}"</p>
								<p>Login: "${item.assignedUser.login}"</p>
								<p>Type: "${item.type}</p>
							</div>
						</td>
						<td></td>
					</tr>
				</c:when>
				<c:when test="${item.state == 'CLOSED'}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>
							<div>
								<p>ID: "${item.id}"</p>
								<p>Title: "${item.title}"</p>
								<p>Login: "${item.assignedUser.login}"</p>
								<p>Type: "${item.type}</p>
							</div>
						</td>
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>
	</table>
</body>
</html>