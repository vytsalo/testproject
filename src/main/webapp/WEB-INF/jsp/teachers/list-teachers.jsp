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



<title>Список преподавателей</title>
</head>
<body onload = "javascript:sortTable()">
<jsp:include page="..\testsecurity\auth.jsp" />

	<section class="container">

<p align = center>
	 Введите данные для поиска <input type="search" class="light-table-filter" data-table="order-table" placeholder="Поиск">
	 <!-- Синюю с прокруткой -->
	</p>

	<div class="table-users">
   <div class="header">Преподаватели</div>

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
         <th>Список групп</th>
         <th>Действия</th>
   		</tr>
	  </thead>

	<c:forEach items="${teachers}" var="list">
		<tr>
          <td><c:out value="${list.id}"/></td>
          <td><c:out value="${list.fam}"/></td>
          <td><c:out value="${list.name}"/></td>
          <td><c:out value="${list.otch}"/></td>
          <td><c:out value="${list.dateOfBirth}"/></td>
          <td><c:out value="${list.phoneNumber}"/></td>
		  <td>
		  
		  <c:forEach items="${list.groups}" var="gslist">
			  <a href = "<c:out value="http://212.193.37.103:8082/groups/update/${gslist.id}"/>" >
				${gslist.title}
			  </a></br>
          
		  </c:forEach>
		  
		  
		  
		  </td>
          <td>


            <center>
            <a href = "<c:out value="http://212.193.37.103:8082/teachers/update/${list.id}"/>" >Редактировать</a>
            </br>
            <a href = "<c:out value="http://212.193.37.103:8082/teachers/delete/${list.id}"/>" >Удалить</a>

            </center>


          </td>
        </tr>
		</c:forEach>



   </table>
</div>

	</section>

<br/>

<p  align=center>
  <a href = "http://212.193.37.103:8082/teachers/add">Добавить</a>
    <br/>
  <a href = "http://212.193.37.103:8082/" align = center >На главную</a>

</p>




</body>
</html>