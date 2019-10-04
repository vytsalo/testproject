<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>

<p>
<!-- Если нет авторизации - поле логина -->

<c:choose>
	<c:when test="${pageContext.request.remoteUser != null}">
		<br/>
		<h3 align = right>Пользователь: ${pageContext.request.remoteUser} | <a href="<c:url value="/logout" />" >Выйти</a></h3>
	</c:when>

    <c:otherwise>
        <br/>
    	<h3 align = right><a href="<c:url value="/login" />">Войти в систему</a></h3>
    </c:otherwise>


</c:choose>



	<!-- ОЗЕРВИЗЕ ВОЙТИ В СИСТЕМУ -->

	<!-- Alternative
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>Добро пожаловать : ${pageContext.request.userPrincipal.name != null}</h2>
	</c:if>
 	-->

</p>

</body>
</html>