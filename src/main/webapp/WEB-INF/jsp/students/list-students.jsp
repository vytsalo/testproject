<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>
<head>


 <link rel="stylesheet" type="text/css" href="<c:url value="\css\tables.css" />" />

        <!-- Подключение js-файла поиска -->
<script src="<c:url value="\js\search.js" />"></script>


</head>
<body>
  <!--for demo wrap-->

	<section class="container">




<p align = center>
	 Введите данные для поиска <input type="search" class="light-table-filter" data-table="order-table" placeholder="Поиск">
	 <!-- Синюю с прокруткой -->
	</p>
	<div class="table-users">
   <div class="header">Студенты</div>

   <table cellspacing="0" class="order-table table">

	  <thead>
	    <tr>
         <th>#ID</th>
         <th>Фамилия</th>
         <th>Имя</th>
         <th>Отчество</th>
         <th>Дата рождения</th>
         <th>Телефон</th>
         <th>Действия</th>
   		</tr>
	  </thead>


	<c:forEach items="${students}" var="list">
		<tr>
          <td><c:out value="${list.id}"/></td>
          <td><c:out value="${list.fam}"/></td>
          <td><c:out value="${list.name}"/></td>
          <td><c:out value="${list.otch}"/></td>
          <td><c:out value="${list.date_of_birth}"/></td><!-- Формат даты поменять? -->
          <td><c:out value="${list.phone_number}"/></td>
          <td>

            <a href = "<c:out value="http://localhost:8082/students/update/${list.id}"/>" >Редактировать</a>
            </br>
            <a href = "<c:out value="http://localhost:8082/students/delete/${list.id}"/>" >Удалить</a>

          </td>
        </tr>
		</c:forEach>



   </table>
</div>


	</section>

<br/>

<p  align=center>
  <a href = "http://localhost:8082/students/add">Добавить</a>
</p>

<!--
<a href = "http://localhost:8082/" align = center >На главную</a>
-->

</body>
</html>