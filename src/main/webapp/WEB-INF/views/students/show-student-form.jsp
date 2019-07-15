<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>
<body>
<h3>Редактирование студента</h3>

<form method="GET" action="http://localhost:8082/students/add>
    <p>Id<input type="text" value="" /></p>
    <p>Фамилия<input type="text" value="" /></p>
    <p>Имя<input type="text" value="" /></p>
    <p>Отчество<input type="text" value="" /></p>
    <p>Дата рождения<input type="text" value="" /></p>
    <p>Телефон<input type="text" value="" /></p>
    <p>Группа<input type="text" value="" /></p>
    <!-- Как реализовать? комбобокс? -->

    <input type="submit" onclick="" style="cursor:pointer"/>

</form>

</body>
</html>