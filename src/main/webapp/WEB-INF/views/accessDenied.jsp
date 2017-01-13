<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>AccessDenied page</title>
</head>

<body>
	Dear
	<strong>${loggedUser}</strong>, You are not authorized to access this
	page
	<a href="<c:url value="/dashboard" />">Dashboard</a>
	<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>