<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<title>Item Form</title>
</head>

<body>
	<%@include file="../jspf/menu.jspf"%>

	<div class="element">
		<h2>Item Form</h2>

		<form:form method="POST" modelAttribute="item">
			<table>
				<tr>
					<td><label for="title">Title: </label></td>
					<td><form:input path="title" id="title" placeholder="Enter Title" required="required" /></td>
					<td><form:errors path="title" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label>Type: </label></td>
					<td><form:select path="type" id="type">
							<form:options items="${types}" />
						</form:select></td>
					<td><form:errors path="type" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label>Priority: </label></td>
					<td><form:select path="priority" id="priority">
							<form:option value="1">1</form:option>
							<form:option value="2">2</form:option>
							<form:option value="3">3</form:option>
							<form:option value="4">4</form:option>
							<form:option value="5">5</form:option>
						</form:select></td>
				</tr>

				<tr>
					<td><label for="severity">Severity: </label></td>
					<td><form:select path="severity" id="severity">
							<form:option value="1">1</form:option>
							<form:option value="2">2</form:option>
							<form:option value="3">3</form:option>
						</form:select></td>
				</tr>

				<tr>
					<td><label>Owner: </label></td>
					<td><form:select path="assignedUser" id="assignedUser">
							<form:options items="${users}" itemValue="login"
								itemLabel="login" />
						</form:select></td>
					<td><form:errors path="assignedUser" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label for="body">Message: </label></td>
					<td><form:textarea path="body" id="body" placeholder="Enter description"/></td>
					<td><form:errors path="body" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label>Original estimate: </label></td>
					<td><form:input path="originalEstimate" id="originalEstimate"/></td>
					<td><form:errors path="originalEstimate" cssClass="error" /></td>
				</tr>

				<tr>
					<td><label>Tags: </label></td>
					<td>${item.tags}</td>
				</tr>

				<tr>
					<td colspan="1"><input type="submit" value="Save" /></td>
					<td colspan="1"><a href="dashboard"><input type="button"
							value="Cancel" /></a></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>