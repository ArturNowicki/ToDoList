<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item Details</title>
</head>
<body>
	<%@include file="../jspf/menu.jspf"%>
	<h2>Item Details</h2>
	ID: "${item.id}"
	<br /> Title: "${item.title}"
	<br /> Type: "${item.type}"
	<br /> Priority: "${item.priority}"
	<br /> Severity: "${item.severity}"
	<br /> Owner: "${item.assignedUser.login}"
	<br /> Message: "${item.body}"
	<br /> State: "${item.state}"
	<br /> Created: "${item.created}"
	<br /> Modified: "${item.modified}"
	<br /> Original estimate: "${item.originalEstimate}"
	<br /> Completed hours: "${item.completedHours}"
	<br /> Remaining hours: "${item.remainingHours}"
	<br />
	<br />
	<form action='edit-${item.id}-item' method="get">
		<input type="submit" value="Edit" />
		<a href="dashboard"><input type="button" value="Back"/></a>
	</form>
</body>
</html>