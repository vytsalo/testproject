<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>
<body>

<head>
<meta charset="ISO-8859-1">
<title>Spring MVC 5 - form handling | Java Guides</title>
<!-- добавить resource folder в помник -->

<!-- абсолютный путь -->
<!-- сделать путь с локальными файлами-->
<!-- . - точка на уровень выше подняться -->


<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js" />"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" />"></script>

</head>

<h3>
<c:choose>
  <c:when test="${update}">
    <c:out value="Редактирование группы с ID = ${group.id}" />

    <!-- Загрузка данных из бд -->
  </c:when>
  <c:otherwise>
    <c:out value="Добавление группы"/>
  </c:otherwise>
</c:choose>
</h3>



<div class="container">
  <div class="col-md-offset-2 col-md-7">
   <h2 class="text-center">Spring MVC 5 + Hibernate 5 + JSP + MySQL
    Example</h2>
   <div class="panel panel-info">

    <div class="panel-heading">

     <div class="panel-title">Add Customer</div>

    </div>
    <div class="panel-body">







<!-- ссылаться на контроллер! -->
<!-- modelAttribute как в контроллере! -->

<!-- Разные версии для добавления и редактирования(загрузить уже имеющиеся данные) -->
<!--
//интерфейс страницы
//https://www.javaguides.net/2018/11/spring-mvc-5-hibernate-5-jsp-mysql-crud-tutorial.html
-->

<!-- в экшон можно просто метод processForm -->
<!-- передается в пост метод джававский для обработки -->









<form method="POST" action="http://localhost:8082/groups/processform" modelAttribute="group" class="form-horizontal">

    <!-- Загружаются данные, если это update -->
    <!-- передается айди с апдейта -->
    <input type="text" value="${group.id}" name="id" hidden />




 <div class="form-group">

        <label for="firstname" class="col-md-3 control-label">Название</label>

        <div class="col-md-9">

         <input type="text" path="firstName" value="${group.title}" name="title" class="form-control" />
        </div>

 </div>







    <!-- Как реализовать? списки-->

    <p>Преподаватели<input type="text" value="" /></p><!-- name -->
    <p>Студенты<input type="text" value="" /></p><!-- name -->


<!-- БАГ - по количеству нажатий на кнопку добавляется несколько записей -->
    <input type="submit" style="cursor:pointer" />

</form>

<!-- поиск с предложением -->
















	    </div>
   </div>
  </div>
 </div>
















</body>
</html>