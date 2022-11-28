<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">

<div align="center">
<h1>Reset Password</h1>

<c:if test="${param.invalidPassword !=null}">
<i style="color: red;">Invalid Old password</i>
</c:if>
<c:if test="${param.newPasswordNotMatch !=null}">
<i style="color: red;">New Password and confirm password does not match</i>
</c:if>


<form:form action="save-change-password" method="POST" modelAttribute="password-chng">
<label>Old password</label>
<form:input path="oldPassword"/>
<br/>
<label>New Password</label>
<form:input path="newPassword"/>
<br/>
<label>Confirm Password</label>
<form:input path="confirmPassword"/>
<br/>
<input type="submit" value="Change Password"/>
</form:form>
</div>

</div>
</body>
</html>