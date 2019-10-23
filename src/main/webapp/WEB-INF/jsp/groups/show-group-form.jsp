<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" type="text/css" href="../../css/style.css" />
    <link rel="stylesheet" type="text/css" href="../../css/validation.css" />
    <script src="../../js/tableOperationz.js"></script>





    <!-- AJAXES -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>


    <!-- sendAjax(id класса) -->
    <script src="../../js/ajaxOperationsTeachers.js"></script>



	<title>Добавление/удаление группы</title>

</head>

<body>
<jsp:include page="../testsecurity/auth.jsp" />
     <div id="signup-form">

        <div id="signup-inner">
        
        	<div class="clearfix" id="header">
        	
        		
			    <img id="signup-icon" src="../../images/group.png" alt="" />

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

            <form:form method="POST" action="../../groups/processform" modelAttribute="group" id="send">

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
                            <form:hidden path="teachers[${tagStatus.index}].dateOfBirth" value="${tcsList.dateOfBirth}" class = "jstlTeachersSending" />
                            <form:hidden path="teachers[${tagStatus.index}].phoneNumber" value="${tcsList.phoneNumber}" class = "jstlTeachersSending" />
                   		
								${tcsList.id}
							
							</td>

                            <td>${tcsList.fam}</td>
                            <td>${tcsList.name}</td>
                            <td>${tcsList.otch}</td>
                            <td>${tcsList.dateOfBirth}</td>
                            <td>${tcsList.phoneNumber}</td>

                            <td><a href = "#" onclick = "addRowToTeachersTable(this,'existingTeachers'); rewriteHTMLTeacher(); return false; ">Удалить</a></td>
                             

                        </tr>
					</c:forEach>
                   

                </tbody>

                </table>

				

<br/>
<br/>
<br/>
<br/>


<%--<h4>
	Список существующих преподавателей
</h4>--%>


<!--notInGroupTeachers -->

	<p>

        <center>

            Введите критерий поиска:
            <br/>


            <div class = "myDiv1">

                <input type = "text" id="searchString">
                <input type = "button" value = "Поиск" onclick = "sendAjax();">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id = "csrf" />

            </div>
            <br/>



            <div id = "results">

                Результаты:

                <table border = "1 px solid" id="existingTeachers" visible = "false">
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

                    <tbody>

                    </tbody>

                </table>

            <p style='color:red;text-align:center;'>Нет результатов.<br/> Попробуйте смягчить условия поиска</p>
        </div>


         </center>







							
				</p>
                				<!--/TEACHERS -->
				
				
				
				<!-- STUDENTS -->


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
                            <form:hidden path="students[${tagStatus.index}].dateOfBirth" value="${stdList.dateOfBirth}" class = "jstlStudentsSending" />
                            <form:hidden path="students[${tagStatus.index}].phoneNumber" value="${stdList.phoneNumber}" class = "jstlStudentsSending" />
                            <form:hidden path="students[${tagStatus.index}].gruppa" value="${stdList.gruppa.id}" class = "jstlStudentsSending" />

                            <div>${stdList.id}</div>
							
							</td>


                            <td>${stdList.fam}</td>
                            <td>${stdList.name}</td>
                            <td>${stdList.otch}</td>
                            <td>${stdList.dateOfBirth}</td>
                            <td>${stdList.phoneNumber}</td>

                            <!-- Все поля hidden а вывод в таблицу отдельно? -->
                            <!-- передается только тайтл -->


                            <td><a href = "#" onclick = "addRow(this,'existingStudents'); rewriteHTML(); return false;">Удалить</a></td>





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
	
	<c:forEach items="${notInGroupStudents}" var="sddList" varStatus="tagStatus">

	<!-- поменять класс и добавить путь  -->
	<tr>
         <td>
		 
		       <input type = "hidden" value= "${sddList.id}" class = "jstlStudentsExisting"/>
				<input type = "hidden" value= "${sddList.name}" class = "jstlStudentsExisting"/>
		 		
		<input type = "hidden" value= "${sddList.fam}" class = "jstlStudentsExisting"/>		   
		  
		       <input type = "hidden" value= "${sddList.otch}" class = "jstlStudentsExisting"/>
		       <input type = "hidden" value= "${sddList.dateOfBirth}" class = "jstlStudentsExisting"/>
		       <input type = "hidden" value= "${sddList.phoneNumber}" class = "jstlStudentsExisting"/>
		       <input type = "hidden" value= "${sddList.gruppa.id}" class = "jstlStudentsExisting"/>
		   

		 <!-- innerHtml = код -->
		 
		 
		 
		 ${sddList.id}</td>
         <td>${sddList.fam}</td>
         <td>${sddList.name}</td>
         <td>${sddList.otch}</td>
         <td>${sddList.dateOfBirth}</td>
         <td>${sddList.phoneNumber}</td>
         <td><a href="#" onclick="addRow(this,'studentsTable'); classChange(); rewriteHTML(); return false;">Добавить</a></td>
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






				<!-- /STUDENTS -->
				
				
				
				
				
				
				
				
				
				
				
				
				
				

	     
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
