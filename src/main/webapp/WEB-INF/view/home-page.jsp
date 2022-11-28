<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>title</title>
</head>
<body>
<h1>Home Page</h1>
<h1>Hi ${username} </h1>
<h1>Role Assigne : ${role}</h1>

<sec:authorize access='hasAuthority("Trainer")'>
<a href="/mvcspringsecurity/trainer">Show Trainer's Dash Board</a>
</sec:authorize>
<br>
<sec:authorize access='hasAuthority("Coder")'>
<a href="/mvcspringsecurity/coder">Show Coder's Dash Board</a>
</sec:authorize>

<br>
<a href="/mvcspringsecurity/deleteUser?username=${username}">Delete Account</a>
&nbsp;

<a href="/mvcspringsecurity/changePassword"> Change Password</a>
<br>
<br>
<br>
<form:form action="logout" method="POST">
<input type="submit" value="logout">
</form:form>
</body>
</html>