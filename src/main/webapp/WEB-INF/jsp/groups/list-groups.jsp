<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- change TAGLIB URI -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>



<link rel="stylesheet" type="text/css" href="../css/tables.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.modal.min.css" />

        <!-- Подключение js-файла поиска -->
<script src="../js/search.js"></script>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/jquery.modal.min.js"></script>
<script src="../js/jquery.tablesorter.min.js"></script>
<script src="../js/tableOperations.js"></script>



<title>Список групп</title>
</head>
<body onload = "javascript:sortTable()">
<jsp:include page="../security/auth.jsp" />


	<section class="container">

<p align = center>
	 Введите данные для поиска <input type="search" class="light-table-filter" data-table="order-table" placeholder="Поиск">
	 <!-- Синюю с прокруткой -->
	</p>

	<div class="table-users">
   <div class="header">Группы</div>

   <table cellspacing="0" class="order-table table" id = "mytable">

	  <thead>
        <tr>
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

                        <a href = "../teachers/update/${tslist.id}" >


                                ${tslist.fam} ${tslist.name} ${tslist.otch}


                                    <fmt:formatDate value="${tslist.dateOfBirth}" pattern="dd.MM.yyyy" />


                                    <br/>

            </a>

                  </c:forEach>

          </td>


           <td>


                          <c:forEach items="${list.students}" var="stlist">

                                    <a href = "../students/update/${stlist.id}" >
${stlist.fam} ${stlist.name} ${stlist.otch}

    <fmt:formatDate value="${stlist.dateOfBirth}" pattern="dd.MM.yyyy" />


    <!--
    -->
                                                               </br>

                                     </a>

                          </c:forEach>

           </td>

			<td>
			<center>
		    <a href = "update/${list.id}">Редактировать</a>
            </br>

            <a href = "delete/${list.id}">Удалить</a>
			</center>

			
			</td>


          </tr>

	</c:forEach>

		</tbody>
	
	</table>
	
	
	</div>
	</section>

<br/>

<p  align=center>
  <a href = "add">Добавить</a>
    <br/>
  <a href = "../" align = center >На главную</a>

</p>




</body>
</html>