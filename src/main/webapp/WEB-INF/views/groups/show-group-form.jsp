<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>
<body>


<!-- if update true - Редактирование с id таким - то -->

<h3>
<c:choose>
  <c:when test="${update}">
    <c:out value="Обновление группы"/>

    <!-- Загрузка данных из бд -->
  </c:when>
  <c:otherwise>
    <c:out value="Добавление группы"/>
  </c:otherwise>
</c:choose>
</h3>


<!-- ссылаться на контроллер! -->
<!-- modelAttribute как в контроллере! -->
<!-- add без айди -->

<!-- Разные версии для добавления и редактирования(загрузить уже имеющиеся данные) -->
<!--
//интерфейс страницы
//https://www.javaguides.net/2018/11/spring-mvc-5-hibernate-5-jsp-mysql-crud-tutorial.html
-->


<!-- в экшон можно просто метод processForm -->
<form method="POST" action="http://localhost:8082/groups/processform" modelAttribute="group">

    <p>Название<input type="text" value="" name="title" /></p>

    <!-- Как реализовать? списки-->

    <p>Преподаватели<input type="text" value="" /></p><!-- name -->
    <p>Студенты<input type="text" value="" /></p><!-- name -->

    <input type="submit" onclick="" style="cursor:pointer"/>

</form>

<!-- поиск с предложением -->

</body>
</html>