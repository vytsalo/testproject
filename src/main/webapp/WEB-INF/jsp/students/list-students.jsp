<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>
<head>



<link rel="stylesheet" type="text/css" href="<c:url value="\css\tables.css" />" />

<link rel="stylesheet" type="text/css" href="<c:url value="\css\jquery.modal.min.css" />" />

        <!-- Подключение js-файла поиска -->
<script src="<c:url value="\js\search.js" />"></script>
<script src="<c:url value="\js\jquery-3.4.1.min.js" />"></script>
<script src="<c:url value="\js\jquery.modal.min.js" />"></script>
<script src="<c:url value="\js\jquery.tablesorter.min.js" />"></script>
<script src="<c:url value="\js\tableOperations.js" />"></script>



<title>Список студентов</title>
</head>
<body onload = "javascript:sortTable()">
<jsp:include page="..\testsecurity\auth.jsp" />

  <!--for demo wrap-->



	<section class="container">

<p align = center>
	 Введите данные для поиска <input type="search" class="light-table-filter" data-table="order-table" placeholder="Поиск">
	 <!-- Синюю с прокруткой -->
	</p>

	<div class="table-users">
   <div class="header">Студенты</div>

   <table cellspacing="0" class="order-table table" id = "mytable">

	  <thead>
	    <tr>
	    <!--

	    <img src = "<c:url value="\images\sortImages.png" />" />

	    -->
         <th>#ID</th>
         <th>Фамилия</th>
         <th>Имя</th>
         <th>Отчество</th>
         <th>Дата рождения</th>
         <th>Телефон</th>
         <th>Группа</th>
         <th>Действия</th>
   		</tr>
	  </thead>

	<c:forEach items="${students}" var="list">
		<tr>
          <td><c:out value="${list.id}"/></td>
          <td><c:out value="${list.fam}"/></td>
          <td><c:out value="${list.name}"/></td>
          <td><c:out value="${list.otch}"/></td>
          <td><c:out value="${list.dateOfBirth}"/></td><!-- Формат даты поменять? -->
          <td><c:out value="${list.phoneNumber}"/></td>
          <td>
          <a href = "http://212.193.37.103:8082/groups/update/${list.gruppa.id}" >
          <c:out value="${list.gruppa.title}"/>
          </a>

          </td>




          <td>



<!-- Само модальное окно -->
<div id="<c:out value="ex${list.id}"/>" class="modal">
  <p>Студент:</p>
  <p>ID : <c:out value="${list.id}"/></p>
  <p>ФИО: <c:out value="${list.fam} ${list.name} ${list.otch}"/></p>
  <p>Дата рождения: <c:out value="${list.dateOfBirth}"/></p>
  <p>Телефон: <c:out value="${list.phoneNumber}"/></p>
  <p>Группа: <c:out value="${list.gruppa}"/></p>
</div>

<!-- Link to open the modal -->


<center>
<a href="<c:out value="#ex${list.id}"/>" rel="modal:open">Информация</a>


            <!-- Перейти на страницу -->








            </br>

            <a href = "<c:out value="http://212.193.37.103:8082/students/update/${list.id}"/>" >Редактировать</a>
            </br>
            <a href = "<c:out value="http://212.193.37.103:8082/students/delete/${list.id}"/>" >Удалить</a>
</center>
          </td>
        </tr>
		</c:forEach>



   </table>
</div>

	</section>

<br/>

<p  align=center>
  <a href = "http://212.193.37.103:8082/students/add">Добавить</a>
    <br/>
  <a href = "http://212.193.37.103:8082/" align = center >На главную</a>

</p>




</body>
</html>