<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/mainpage.css" />

<title>Единое информационное пространство</title></head>
<body>

<jsp:include page="security/auth.jsp" />

<div class="pricingTable">
  <h2 class="pricingTable-title">Единое информационное пространство</h2>
  <h3 class="pricingTable-subtitle">Выберите страницу для перехода</h3>

  <ul class="pricingTable-firstTable">

	<li class="pricingTable-firstTable_table">
      <h1 class="pricingTable-firstTable_table__header">Студенты</h1>
       <br/>
      <h4>Информация:</h4>
      <ul class="pricingTable-firstTable_table__options">
        <li>ФИО</li>
        <li>Дата рождения</li>
        <li>Телефон</li>
        <li>Группа</li>
      </ul>
      <div class="pricingTable-firstTable_table__getstart" onclick ="window.location='students/'" >Перейти</div>
    </li>


	<li class="pricingTable-firstTable_table">
      <h1 class="pricingTable-firstTable_table__header">Группы
      </h1>
      <br/>
      <h4>Информация:</h4>
      <ul class="pricingTable-firstTable_table__options">
        <li>Название группы</li>
        <li>Список студентов</li>
        <li>Список преподавателей</li>
      </ul>
      <div class="pricingTable-firstTable_table__getstart" onclick ="window.location='groups/'">Перейти</div>
    </li>


	<li class="pricingTable-firstTable_table">
      <h1 class="pricingTable-firstTable_table__header">Преподаватели</h1>
       <br/>
      <h4>Информация:</h4>
      <ul class="pricingTable-firstTable_table__options">
        <li>ФИО</li>
        <li>Дата рождения</li>
        <li>Телефон</li>
        <li>Список групп</li>
      </ul>
      <div class="pricingTable-firstTable_table__getstart" onclick ="window.location='teachers/'">Перейти</div>
    </li>


  </ul>
</div>

</body>
</html>