<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/auth.css" />
	<link rel="stylesheet" type="text/css" href="../css/button.css" />
</head>
<body>



<nav>
	<a href="#" class="button" align = right >Войти</a>
</nav>


<p>
<!-- Если нет авторизации - поле логина -->

<c:choose>
	<c:when test="${pageContext.request.remoteUser != null}">
		<br/>
		<h3 class="auth-button">Пользователь: ${pageContext.request.remoteUser} |


	<a href="javascript:document.forms['auth'].submit()">Выйти</a>

</h3>


<form:form action="${pageContext.request.contextPath}/logout"
		   method="post" modelAttribute="_csrf" name='auth'>
	<form:button value="submit" hidden = "hidden"></form:button>

</form:form>




</c:when>

    <c:otherwise>
        <br/>
    	<h3 class="auth-button"><a href="<c:url value="/login" />">Войти в систему</a></h3>
    </c:otherwise>


</c:choose>

</p>

</body>
</html>