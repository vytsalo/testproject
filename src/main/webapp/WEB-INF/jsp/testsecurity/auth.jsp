<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>

<p>
<!-- Если нет авторизации - поле логина -->


	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h3 align = right>Пользователь: ${pageContext.request.userPrincipal.name} | <a href="<c:url value="/logout" />" >Выйти</a></h3>
	</c:if>


	<!-- Alternative
	<c:if test="${pageContext.request.remoteUser != null}">
		<h2>Добро пожаловать : ${pageContext.request.remoteUser}</h2>
	</c:if>
 	-->

</p>

</body>
</html>