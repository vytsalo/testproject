<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<style>

body {
    margin: 0;
    font-family: Arial, Helvetica, sans-serif;
}

.header {
    overflow: hidden;
    background: linear-gradient(to bottom right, #37F7F5, #C4BAF2);
    padding: 5px 5px;
}

.header a {
    float: left;
    color: black;
    text-align: center;
    padding: 10px;
    text-decoration: none;
    font-size: 16px;
    line-height: 20px;
    border-radius: 4px;
}

.header a.logo {
    font-size: 20px;
    font-weight: bold;
}

.header a:hover {
    background-color: #bbdefb;
    color: black;
}

.header-right {
    float: right;
    margin-right: 15%;

}

#eip-logo {
    width: 48px;
    height: 48px;
    vertical-align:middle;
}

a.green{
    border-radius: 4px;
    box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.08);
    color: #fff;
    display:block;
    text-align: center;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 14px;
    padding: 8px 16px;
    margin: 15px auto;
    text-decoration: none;
    text-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
}
a.green {
    background-color: rgb( 43, 153, 91 );
    border: 1px solid rgb( 33, 126, 74 );
}

a.green:hover {
    background-color: rgb( 75, 183, 141 );
}


</style>

</head>
<body>

<div class="header" >
<a href="/" class="logo"><img src = "../images/eip_logo.png" id = "eip-logo" /> Единое информационное пространство</a>

<div class="header-right">

        <c:choose>
        <c:when test="${pageContext.request.remoteUser != null}">

<a href="javascript:document.forms['auth'].submit()" class="green" >${pageContext.request.remoteUser} | Выйти</a>


        <form:form action = "${pageContext.request.contextPath}/logout"
                   method = "post" modelAttribute = "_csrf" name = 'auth'>
            <form:button value="submit" hidden = "hidden"></form:button>

        </form:form>

        </c:when>

        <c:otherwise>


        <a href="<c:url value="/login" />" class="green" >Войти в систему</a>


    </c:otherwise>

    </c:choose>




</div>
</div>

</body>
</html>