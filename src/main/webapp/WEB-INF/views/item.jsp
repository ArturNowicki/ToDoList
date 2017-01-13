<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>Item Details</title>
</head>

<body>
	<%@include file="../jspf/menu.jspf"%>
	<div class="element-left">
		<h2>Item Details</h2>
		ID: "${item.id}" <br /> Title: "${item.title}" <br /> Type:
		"${item.type}" <br /> Priority: "${item.priority}" <br /> Severity:
		"${item.severity}" <br /> Owner: "${item.assignedUser.login}" <br />
		Message: "${item.body}" <br /> State: "${item.state}" <br />
		Created: "${item.created}" <br /> Modified: "${item.modified}" <br />
		Original estimate: "${item.originalEstimate}" <br /> Completed hours:
		"${item.completedHours}" <br /> Remaining hours:
		"${item.remainingHours}"
		<!--<br /> Tags: <c:forEach items="${item.tags}" var="tag">
				<input type="button" value="${tag.name}" disabled/> 
				</c:forEach>  -->
		<br /> <br />
		<form action='edit-${item.id}-item' method="get">
			<input type="submit" value="Edit" />
		</form>
		<a href="dashboard"><input type="button" value="Back" /></a>
		<a href='delete-${item.id}-item'
			onclick="return confirm('Delete ${item.title}?')"><input
			type="submit" value="Delete" /></a>
	</div>
</body>
</html>