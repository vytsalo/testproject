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
                
				
				<!-- везде разные переменные -->
				<!--TEACHERS -->
				<table border = "1 px solid" id="teachersTable">
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

                 <c:forEach items="${group.teachers}" var="tcsList" varStatus="tagStatus">

                        <tr>
                            <td>

                            <form:hidden path="teachers[${tagStatus.index}].id" value="${tcsList.id}" class = "jstlTeachersSending" />
                            <form:hidden path="teachers[${tagStatus.index}].name" value="${tcsList.name}" class = "jstlTeachersSending" />
                            <form:hidden path="teachers[${tagStatus.index}].fam" value="${tcsList.fam}" class = "jstlTeachersSending" />
                            <form:hidden path="teachers[${tagStatus.index}].otch" value="${tcsList.otch}" class = "jstlTeachersSending" />
                            <form:hidden path="teachers[${tagStatus.index}].date_of_birth" value="${tcsList.date_of_birth}" class = "jstlTeachersSending" />
                            <form:hidden path="teachers[${tagStatus.index}].phone_number" value="${tcsList.phone_number}" class = "jstlTeachersSending" />
                            <!-- SECOND FOREACH-->
						
						<c:forEach items="${tcsList.groups}" var="gsList" varStatus="dagSdatus">
						
							<form:hidden path="teachers[${tagStatus.index}].groups[${dagSdatus.index}]" value="${gsList.id}" class = "jstlTeachersSending" />
						
						</c:forEach>
						
						
                            <div>${tcsList.id}</div>
                            </td>

                            <td>${tcsList.fam}</td>
                            <td>${tcsList.name}</td>
                            <td>${tcsList.otch}</td>
                            <td>${tcsList.date_of_birth}</td>
                            <td>${tcsList.phone_number}</td>
                            <td><a href = "#" onclick = "addRowToTeachersTable(this,'existingTeachers'); rewriteHTMLTeacher(); ">Удалить</a></td>
                             

                        </tr>
					</c:forEach>
                   

                </tbody>

                </table>

				

<br/>
<br/>
<br/>
<br/>


<h4>
	Список существующих преподавателей
</h4>


<!--notInGroupTeachers -->

<table id="existingTeachers" cellspacing="0" border="1">
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
		
	<c:forEach items="${notInGroupTeachers}" var="tsList" varStatus="tagStatus">

	<!-- поменять класс и добавить путь  -->
	<tr>
         <td>
		 
		       <input type = "hidden" value= "${tsList.id}" class = "jstlTeachersExisting"/>
				<input type = "hidden" value= "${tsList.name}" class = "jstlTeachersExisting"/>
		 		
		<input type = "hidden" value= "${tsList.fam}" class = "jstlTeachersExisting"/>		   
		  
		       <input type = "hidden" value= "${tsList.otch}" class = "jstlTeachersExisting"/>
		       <input type = "hidden" value= "${tsList.date_of_birth}" class = "jstlTeachersExisting"/>
		       <input type = "hidden" value= "${tsList.phone_number}" class = "jstlTeachersExisting"/>
		       
	
						<!-- tagZdaduz -->
							
							<!-- Каждое поле в отдельности - бесконечная рекурсия -->
	
	
 
				
						<c:forEach items="${tcsList.groups}" var="gsList" varStatus="dagSdatus">
						
							<input type = "hidden" value="${gsList}" class = "jstlTeachersSending" />
						
						</c:forEach>

			
			   

		 <!-- innerHtml = код -->
		 
		 
		 
		 ${tsList.id}</td>
         <td>${tsList.fam}</td>
         <td>${tsList.name}</td>
         <td>${tsList.otch}</td>
         <td>${tsList.date_of_birth}</td>
         <td>${tsList.phone_number}</td>
         <td><a href="#" onclick="addRowToTeachersTable(this,'teachersTable'); classChangeTeacher(); rewriteHTMLTeacher();">Добавить</a></td>
    </tr>
	
	</c:forEach>
	
  </tbody>

</table>​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​
		
				
							
				</p>
                				<!--/TEACHERS -->
				
				
				

	     
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
