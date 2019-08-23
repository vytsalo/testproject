<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>
<body align = center>
<h3>Список всех студентов</h3>


<!-- обращение к модели через атрибуты-->

<c:set var="test" value="Студенты:" />

<!-- Выводить только номер и название группы ссылкой -->

<h4><c:out value="${test}" /></h4>

<!-- база данных -->
<!-- таблицей имя и т.д. -->
<!-- база -->

<c:forEach items="${students}" var="list">

    <c:out value="${list}"/><br>

    <!-- Преподавателей списков выводить -->
    <!--
    <c:out value="${list.id}"/><br>
    <c:out value="${list.fam}"/><br>
    <c:out value="${list.name}"/><br>
    <c:out value="${list.otch}"/><br>
    <c:out value="${list.date_of_birth}"/><br>
    <c:out value="${list.phone_number}"/><br>
    <c:out value="${list.gruppa}"/><br>

    -->


</c:forEach>

<a href = "http://localhost:8082/" >На главную</a>


</body>
</html>