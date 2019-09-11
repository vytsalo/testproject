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

	<section class="container">


	<div class="table-users">
   <div class="header">Группы</div>

   <table cellspacing="0" class="order-table table" id = "mytable">

	  <thead>

         <th>#ID</th>
         <th>Название</th>
         <th>Преподаватели</th>
         <th>Студенты</th>
         <th>Действия</th>
   		</tr>
	  </thead>

	  <tbody>
	<c:forEach items="${groups}" var="list">
		<tr>
          <td><c:out value="${list.id}"/></td>
          <td><c:out value="${list.title}"/></td>

          <td>


              <c:forEach items="${list.teachers}" var="tslist">

                        <a href = "http://localhost:8082/teachers/update/${tslist.id}" >

                              <c:out value="${tslist}"/><br>
                         </a>

                  </c:forEach>

          </td>


           <td>


                          <c:forEach items="${list.students}" var="stlist">

                                    <a href = "http://localhost:8082/students/update/${stlist.id}" >

                                          <c:out value="${stlist}"/><br>

                                     </a>

                              </c:forEach>

           </td>

			<td>
			
		    <a href = "<c:out value="http://localhost:8082/groups/update/${list.id}"/>" >Редактировать</a>
            </br>

            <!-- Данная группа содержит студентов или преподавателей. Уверены, что хотите удалить? -->
            <a href = "<c:out value="http://localhost:8082/groups/delete/${list.id}"/>" >Удалить</a>
			
			
			
			
			</td>



          </tr>

	</c:forEach>

		</tbody>
	
	</table>
	
	<p align = center>
	 Введите данные для поиска <input type="search" class="light-table-filter" data-table="order-table" placeholder="Поиск">
	 <!-- Синюю с прокруткой -->
	</p>
	</div>
	</section>

<br/>

<p  align=center>
  <a href = "http://localhost:8082/groups/add">Добавить</a>
    <br/>
  <a href = "http://localhost:8082/" align = center >На главную</a>

</p>




</body>
</html>