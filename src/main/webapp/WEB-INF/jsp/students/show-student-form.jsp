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


        <link rel="stylesheet" type="text/css" href="<c:url value="\css\jquery.modal.min.css" />" />


        <!-- Подключение библиотеки jQuery -->
        <script src="<c:url value="\js\jquery-3.4.1.min.js" />"></script>

        <script src="<c:url value="\js\jquery.modal.min.js" />"></script>


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


                    <!-- -readonly for working validation -->
                   <input type="text" id="group" value="${student.gruppa.title}" readonly
                        onclick = "$('#addGroupWindow').modal('show'); return false;"
                        style="cursor: pointer; vertical-align: 65px;"
                        minlength="2" maxlength="35" required
                    />


                           	<img src="<c:url value="\images\cross.png" />"
                                       	style="cursor: pointer; vertical-align: 40px;"
                                       	    onclick="
                                       	    document.getElementById('group').value='';
                                       	    document.getElementById('groupId').value='';
                                       	    document.getElementById('deleteGroup').style.visibility = 'hidden';
                                       	    return false;"
                                       	alt="" id = "deleteGroup" />

               <springForm:input type="hidden" id = "groupId" value="${student.gruppa.id}" path="gruppa" readonly="readonly" />




                    <!-- vertical-align: 65px; -->



                    <!-- Само модальное окно -->
                    <div id="<c:out value="addGroupWindow"/>" class="modal">
                      <h4>Список групп:</h4>

                              <center>

                                  <table id="mytable" cellspacing="5" border="1">
                                      <thead>
                                  	    <tr>
                                           <th>#ID</th>
                                           <th>Название</th>
                                           <th></th>
                                     	</tr>
                                  	  </thead>


                                    <tbody>



                      <c:forEach items="${groups}" var="lost">
                                		<tr>
                                          <td><c:out value="${lost.id}"/></td>
                                          <td><c:out value="${lost.title}"/></td>
                                          <td>

                               <td>

                               <a href = "#" onclick = "setGroup(this)" >Добавить</a>

                               </td>

                                          </td>
                                      </tr>
                      </c:forEach>

                                    </tbody>

                                  </table>​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​
                      </center>


                    </div>


<!-- в хайдден передать id студента в группе? -->

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