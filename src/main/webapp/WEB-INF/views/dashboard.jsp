<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>Dashboard</title>
</head>

<body>

	<%@include file="../jspf/menu.jspf"%>
	<div class="center dashboard">
		<h2>Dashboard</h2>
		<table class="table">
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
							<td><%@include file="../jspf/itemTile.jspf"%></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:when>
					<c:when test="${item.state == 'ACTIVE'}">
						<tr>
							<td></td>
							<td><%@include file="../jspf/itemTile.jspf"%></td>
							<td></td>
							<td></td>
						</tr>
					</c:when>
					<c:when test="${item.state == 'RESOLVED'}">
						<tr>
							<td></td>
							<td></td>
							<td><%@include file="../jspf/itemTile.jspf"%></td>
							<td></td>
						</tr>
					</c:when>
					<c:when test="${item.state == 'CLOSED'}">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><%@include file="../jspf/itemTile.jspf"%></td>
						</tr>
					</c:when>
				</c:choose>
			</c:forEach>
		</table>
	</div>
	<br />
	<div align="right" style="width: 80%">
		<a href="<c:url value='/newitem'/>">Add Item</a>
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