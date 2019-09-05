<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>

<head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <!-- мб без сиюрла можно? -->
        <link rel="stylesheet" type="text/css" href="<c:url value="\css\style.css" />" />

        <link rel="stylesheet" type="text/css" href="<c:url value="\css\validation.css" />" />


        <!-- Подключение библиотеки jQuery -->
        <script src="<c:url value="\js\jquery-3.4.1.min.js" />"></script>
        <!-- Подключение jQuery плагина Masked Input -->
        <script src="<c:url value="\js\jquery.maskedinput.min.js" />"></script>
        <!-- Подключение методов обработки полей -->
        <script src="<c:url value="\js\mask.js" />"></script>
        <!-- Операции с таблицами -->
        <script src="<c:url value="\js\tableOperations.js" />"></script>


        <title>Добавление/удаление студента</title>

</head>



<body>
<jsp:include page="..\testsecurity\auth.jsp" />


    <div id="signup-form">

        <div id="signup-inner">

        	<div class="clearfix" id="header">

				<img id="signup-icon" src="<c:url value="\images\group.png" />" alt="" />

                    <h1>
	        			<c:choose>
                            <c:when test="${update}">
                                <c:out value="Редактирование студента с ID = ${student.id}" />
                            </c:when>

                        <c:otherwise>
                            <c:out value="Добавление студента"/>
                            </c:otherwise>
                        </c:choose>
    				</h1>
            </div>


			<p>Пожалуйста, заполните поля ниже.</p>

            <!-- Поменять лейбли и айдишники --> <!-- -ID? -->
            <springForm:form method="POST" action="http://localhost:8082/students/processform" modelAttribute="student" id="send">

				<springForm:input type="hidden" value="${student.id}" path="id" />

                         <!-- https://www.journaldev.com/2668/spring-validation-example-mvc-validator -->
                <p>
                    <label for="fam">Фамилия *</label><!-- можно ли связываться с неймом, а не с айди -->
                    <!-- привести в вид атрибутов по порядку -->
                    <springForm:input type="text" id="fam" path="fam" value="${student.fam}" minlength="2" maxlength="35" required="required" /><!-- рекуиред -->
                    <springForm:errors path="fam" cssClass="error" />
                </p>

                <p>
                    <label for="name">Имя *</label>
                    <springForm:input type="text" id="name" path="name" value="${student.name}" minlength="2" maxlength="35" required="required" />
                    <springForm:errors path="name" cssClass="error" />
                </p>

                <p>
                    <label for="otch">Отчество *</label>
                    <springForm:input type="text" path="otch" id="otch" value="${student.otch}" minlength="2" maxlength="35" required="required" />
                    <springForm:errors path="otch" cssClass="error" />
                </p>


                    <!-- Маска -->
                    <!-- https://jsfiddle.net/itchief/9faffnof/ -->
                    <!-- Подсветка полей -->
                    <!-- https://medium.com/russian/%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%B0%D1%86%D0%B8%D1%8F-%D1%84%D0%BE%D1%80%D0%BC-%D0%BD%D0%B0-html-%D0%B8-css-c34c982d42a0 -->
                    <!-- с сообщениями -->
                    <!-- https://professorweb.ru/my/html/html5/level2/2_2.php -->
                    <!-- JS Method Validation -->
                    <!-- https://www.w3schools.com/js/tryit.asp?filename=tryjs_validation_number -->

<!--


validate(){
id=sdksdl
if ()''= Слижком много слижком мало букв в тултип

}


https://www.geeksforgeeks.org/form-validation-using-html-javascript/


//Топовое
https://htmlacademy.ru/blog/useful/html/form-validation-techniques



// Сборка регулярок
https://habr.com/ru/post/123845/


-->

                <p>
                    <label for="date">Дата рождения *</label>

                    <springForm:input type="date" id="date" path="date_of_birth" value="${student.date_of_birth}" required="required" min="1965-01-01" max="2002-12-31"/>
                    <br/>
                    <springForm:errors path="date_of_birth" cssClass="error" />
                </p>

                <p>
                    <label for="phone">Номер телефона *</label>
                    <springForm:input type="text" path="phone_number" id="phone" value="${student.phone_number}" required="required" />
                    <springForm:errors path="phone_number" cssClass="error" /> <!-- cssClass="error" -->
                </p>

                <p>

                    <label for="group">Группа</label>



                   <input type="text" id="group" value="${student.gruppa.title}" readonly
                        onclick = "alert('Поиск в модалку(окно выбор группы)'); return false;"
                        style="cursor: pointer; vertical-align: 65px;"
                    />







<!-- в хайдден передать id студента в группе? -->

                   	<img src="<c:url value="\images\cross.png" />"
                   	style="cursor: pointer; vertical-align: 40px; visibility: hidden;"
                   	    onclick="document.getElementById('group').value='';
                   	    document.getElementById('groupId').value='';
                   	    document.getElementById('deleteGroup').style.visibility = 'hidden';
                   	    return false;"
                   	alt="" id = "deleteGroup" />



        <springForm:input type="hidden" id = "groupId" value="${student.gruppa.id}" path="gruppa" />

            <div style="border: thin solid black">
            <h4>
            	Список существующих групп
            </h4>

        <center>

            <table id="mytable" cellspacing="0" border="1">
                <thead>
            	    <tr>
                     <th>#ID</th>
                     <th>Название</th>
                     <th>Список преподов</th>
                     <th>Список студентов</th>
                     <th>Действия</th>
               		</tr>
            	  </thead>


              <tbody>



<c:forEach items="${groups}" var="lost">
          		<tr>
                    <td><c:out value="${lost.id}"/></td>
                    <td><c:out value="${lost.title}"/></td>
                    <td><c:out value="lost teachers"/></td>
                    <td><c:out value="lost.students"/></td>
                    <td>

         <td><input type="button" value="Add" onclick="setGroup(this)" /></td>

                    </td>
                </tr>
</c:forEach>









              </tbody>

            </table>​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​
</center>

              </div>








                </p>

                <p>
                   <input type="submit" id="submit" value="Отправить" />
                </p>

            </springForm:form>

                <div id="required">
                <p>* Поля, обязательные для заполнения</p>
                </div>


            </div>

        </div>

    </div>

</body>
</html>