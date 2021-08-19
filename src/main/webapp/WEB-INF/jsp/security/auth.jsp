<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<body>

<p>
<!-- Если нет авторизации - поле логина -->

<c:choose>
	<c:when test="${pageContext.request.remoteUser != null}">
		<br/>
		<h3 align = right>Пользователь: ${pageContext.request.remoteUser} |


	<a href="javascript:document.forms['test'].submit()">Выйти</a>

</h3>


<form:form action="${pageContext.request.contextPath}/logout"
		   method="post" modelAttribute="_csrf" name='test'>
	<form:button value="submit" hidden = "hidden"></form:button>

</form:form>






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