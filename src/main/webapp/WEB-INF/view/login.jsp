<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
</head>
<body>

	<!-- write some code to handle the invalid login scinario -->
	<c:if test="${param.error !=null}">
		<i style="color: red">invalid username or password</i>
	</c:if>

	<c:if test="${param.logout !=null}">
		<i style="color: red">You are successfully logged out</i>
	</c:if>

	<h1>My custom login page</h1>

	<form:form action="process-login">
Username:<input type="text" placeholder="username" name="username">
		<br>
Password:<input type="password" placeholder="password" , name="password">
		<br>
		<input type="submit" ,value="login">
	</form:form>
</body>
</html>