<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>
<body>
<h3>Редактирование группы</h3>

<form method="GET" action="http://localhost:8082/groups/add>
    <p>Id<input type="text" value="" /></p>
    <p>Название<input type="text" value="" /></p>
    <!-- Как реализовать? -->
    <p>Преподаватели<input type="text" value="" /></p>
    <p>Студенты<input type="text" value="" /></p>


    <input type="submit" onclick="" style="cursor:pointer"/>



<!--
<form:form action="save" name="employeeDTO" method="POST">
    <label for="name">Name</label><input id="name" name="name" type="text" required><br>
    <label for="surname">Surname</label><input id="surname" name="surname" type="text" required><br>
    <label for="email">E-mail</label><input id="email" type="email" name="email" required><br>
    <label for="salary">Salary</label><input id="salary" type="number" name="salary" required><br>
    <input type="submit" value="Save">
</form:form>
-->

</form>

</body>
</html>