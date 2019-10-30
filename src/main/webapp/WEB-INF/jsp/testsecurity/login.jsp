<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<!-- ../login -->
        <link rel="stylesheet" type="text/css" href="../css/login.css" />

    <title>Вход в систему</title>
</head>
<body>






<div class="login-page">

    <div class="form" >

        <form class="login-form" method="post" action="login">

            <c:if test="${not empty errorMessge}">
                <div style="color:red; font-weight: bold; ">
                        ${errorMessge}
                </div>
            </c:if>


            <!-- Для просмотра этой страницы необходимо авторизоваться -->

            <input type='text' name='username' value='' placeholder="Имя пользователя(логин)"/>
            <input type='password' name='password' placeholder="Пароль"/>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


            <input name="submit" type="submit" value="Войти" />

        </form>
    </div>
</div>

</body>
</html>