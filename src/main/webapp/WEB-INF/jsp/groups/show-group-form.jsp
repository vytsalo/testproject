<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>
<!-- Удалить лишнее, юзаю только джеестеель кор -->
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" type="text/css" href="<c:url value="\css\style.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="\css\validation.css" />" />

    <script src="<c:url value="\js\tableOperationz.js" />"></script>

	<title>Добавление/удаление группы</title>

</head>

<body>

     <div id="signup-form">

        <div id="signup-inner">
        
        	<div class="clearfix" id="header">
        	
        		
			    <img id="signup-icon" src="<c:url value="\images\group.png" />" alt="" />

                    <h1>
                        <c:choose>
                            <c:when test="${update}">
                                <c:out value="Редактирование группы с ID = ${group.id}" />


                            </c:when>
                            <c:otherwise>
                                <c:out value="Добавление группы"/>
                            </c:otherwise>
                        </c:choose>
				
				    </h1>
            
            </div>
			
			
			<p>Пожалуйста, заполните поля ниже.</p>

            <form:form method="POST" action="http://localhost:8082/groups/processform" modelAttribute="group" id="send">

				<form:input type="hidden" value="${group.id}" path="id" />

                <p>
                <label for="title">Название *</label>
                <form:input id="title" path = "title" value="${group.title}" minlength="2" maxlength="35" required = "true" /> <!-- required -->
                </p>

				<p>
                <label for="teachers">Список преподавателей группы</label>
                <input id="teachers" type="text" value="" />
                </p>
                
                <p>
                <label for="students">Список студентов группы</label>


                <table border = "1 px solid" id="studentsTable">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Дата рождения</th>
                    <th>Телефон</th>
                    <th>Действие</th>
                    </tr>
                </thead>
                <!-- group - модель, студенты - аттрибут -->

                <tbody>



                    <c:forEach items="${group.students}" var="stdList" varStatus="tagStatus">


                        <tr>
                            <td>


                            <form:hidden path="students[${tagStatus.index}].id" value="${stdList.id}" class = "jstlStudentsSending" />
                            <form:hidden path="students[${tagStatus.index}].name" value="${stdList.name}" class = "jstlStudentsSending" />
                            <form:hidden path="students[${tagStatus.index}].fam" value="${stdList.fam}" class = "jstlStudentsSending" />
                            <form:hidden path="students[${tagStatus.index}].otch" value="${stdList.otch}" class = "jstlStudentsSending" />
                            <form:hidden path="students[${tagStatus.index}].date_of_birth" value="${stdList.date_of_birth}" class = "jstlStudentsSending" />
                            <form:hidden path="students[${tagStatus.index}].phone_number" value="${stdList.phone_number}" class = "jstlStudentsSending" />
                            <form:hidden path="students[${tagStatus.index}].gruppa" value="${stdList.gruppa.id}" class = "jstlStudentsSending" />

                            <div>${stdList.id}</div>
                            </td>

                            <!-- js переделать формирование страницы
                             переписать код страницы

                             -->

                            <!-- /HIDDENS  -->

                            <td>${stdList.fam}</td>
                            <td>${stdList.name}</td>
                            <td>${stdList.otch}</td>
                            <td>${stdList.date_of_birth}</td>
                            <td>${stdList.phone_number}</td>

                            <!-- Все поля hidden а вывод в таблицу отдельно? -->
                            <!-- передается только тайтл -->


                            <td><a href = "#" onclick = "addRow(this,'existingStudents'); rewriteHTML(); ">Удалить</a></td>





                        </tr>



                    </c:forEach>




                </tbody>

                </table>






                    














<br/>
<br/>
<br/>
<br/>






<h4>
	Список существующих студентов
</h4>



<!--notInGroupStudents -->

<table id="existingStudents" cellspacing="0" border="1">
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
  
  
  <tbody>
		<!-- th to td -->
		
		
		<!-- notInGroupStudents -->

		<!-- Добавлять скрытые инпуты, а потом при переносе в таблицу - добавлять 
		добавлять класс
		name=""  -->
		
	<c:forEach items="${notInGroupStudents}" var="sddList">

	
	<tr>
         <td>${sddList.id}</td>
         <td>${sddList.fam}</td>
         <td>${sddList.name}</td>
         <td>${sddList.otch}</td>
         <td>${sddList.date_of_birth}</td>
         <td>${sddList.phone_number}</td>
         <td><a href="#" onclick="addRow(this,'studentsTable')">Добавить</a></td>
    </tr>
	
	</c:forEach>
	
  </tbody>

</table>​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​





























<br/>
<br/>
<br/>
<br/>
<br/>
<br/>


                </p>
                
                <p>
                <input type="submit" id="submit" value="Отправить" />
                </p>
                
            </form:form>
            
		<div id="required">
		<p>* Поля, обязательные для заполнения</p>
		</div>


            </div>

        </div>

    </div>

</body>
</html>
