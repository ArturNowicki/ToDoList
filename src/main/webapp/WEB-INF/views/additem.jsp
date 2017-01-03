<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Form</title>
<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>
	<%@include file="../jspf/menu.jspf" %>

	<h2>Item Form</h2>

	<form:form method="POST" modelAttribute="item">
		<form:input type="hidden" path="id" id="id" />
		<form:input type="hidden" path="created" id="created" />
		<form:input type="hidden" path="originalEstimate" id="originalEstimate" />
		<table>
			<tr>
				<td><label for="title">Title: </label></td>
				<td><form:input path="title" id="title" /></td>
				<td><form:errors path="title" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="type">Type: </label></td>
				<td><form:select path="type" id="type">
					<form:options items="${types}"/>
				</form:select></td>
				<td><form:errors path="type" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="priority">Priority: </label></td>
				<td><form:input path="priority" id="priority" /></td>
				<td><form:errors path="priority" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="severity">Severity: </label></td>
				<td><form:input path="severity" id="severity" /></td>
				<td><form:errors path="severity" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><label for="assignedUser">Owner: </label></td>
				<td><form:select path="assignedUser" id="assignedUser">
					<form:options items="${users}" itemValue="login" itemLabel="login"/>
				</form:select></td>
				<td><form:errors path="assignedUser" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="body">Message: </label></td>
				<td><form:input path="body" id="body" /></td>
				<td><form:errors path="body" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><label for="state">State: </label></td>
				<td><form:select path="state" id="state">
					<form:options items="${types}"/>
				</form:select></td>
				<td><form:errors path="state" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="completedHours">Completed hours: </label></td>
				<td><form:input path="completedHours" id="completedHours" /></td>
				<td><form:errors path="completedHours" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><label for="remainingHours">Remaining hours: </label></td>
				<td><form:input path="remainingHours" id="remainingHours" /></td>
				<td><form:errors path="remainingHours" cssClass="error" /></td>
			</tr>

			<tr>
				<td colspan="1"><input type="submit" value="Save" /></td>
				<td colspan="1"><a href="item-${id}"><input type="button" value="Cancel"/></a></td>
				
			</tr>
		</table>
	</form:form>
</body>
</html>