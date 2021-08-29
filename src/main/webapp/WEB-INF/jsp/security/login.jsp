<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>" />

        <title>Вход в систему</title>
    </head>
<body>






<div class="login-page">

    <div class="form" >

        <form class="login-form" method="post" action="login">

            <c:if test="${not empty errorMessage}">
                <div style="color:red; font-weight: bold;
    animation: blink 1s;
    animation-iteration-count: 3;">
                        ${errorMessage}
                </div>
                <br/>
            </c:if>


            <!-- Для просмотра этой страницы необходимо авторизоваться -->

            <input type = 'text' name = 'username' value = '' placeholder = "Имя пользователя(логин)"/>
            <input type = 'password' name = 'password' placeholder = "Пароль"/>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


            <input name="submit" type="submit" value = "Войти" />

        </form>
    </div>
</div>

</body>
</html>