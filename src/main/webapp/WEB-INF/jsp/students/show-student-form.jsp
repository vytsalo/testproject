<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>

<head>








    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>" />

        <link rel="stylesheet" type="text/css" href="../css/validation.css" />


        <link rel="stylesheet" type="text/css" href="../css/jquery.modal.min.css" />


        <!-- Подключение библиотеки jQuery -->
        <script src="../js/jquery-3.4.1.min.js"></script>

        <script src="../js/jquery.modal.min.js"></script>


        <!-- Подключение jQuery плагина Masked Input -->
        <script src="../js/jquery.maskedinput.min.js"></script>
        <!-- Подключение методов обработки полей -->
        <script src="../js/mask.js"></script>
        <!-- Операции с таблицами -->
        <script src="../js/tableOperations.js"></script>
        <!-- операции с таблицами студентов -->
        <script src="../js/tableOperationz.js"></script>

        <!--  -->
        <script src="../js/ajaxOperationsGroup.js"></script>


        <title>Добавление/удаление студента</title>

</head>



<body>
<jsp:include page="../testsecurity/auth.jsp" />


    <div id="signup-form">

        <div id="signup-inner">

        	<div class="clearfix" id="header">

				<img id="signup-icon" src="../images/group.png" alt="" />

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
            <springForm:form method="POST" action="../students/processform" modelAttribute="student" id="send">

				<springForm:input type="hidden" value="${student.id}" path="id" />
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

                    <springForm:input type="date" id="date" path="dateOfBirth" value="${student.dateOfBirth}" required="required" min="1950-01-01" max="2005-12-31" />
                    <br/>
                    <springForm:errors path="dateOfBirth" cssClass="error" />
                </p>

                <p>
                    <label for="phone">Номер телефона *</label>
                    <springForm:input type="text" path="phoneNumber" id="phone" value="${student.phoneNumber}" required="required" />
                    <springForm:errors path="phoneNumber" cssClass="error" /> <!-- cssClass="error" -->
                </p>

                <p>

                    <label for="group">Группа</label>

                   <input type="text" id="group" value="${student.gruppa.title}" readonly
                        onclick = "$('#addGroupWindow').modal('show'); return false;"
                        style="cursor: pointer; vertical-align: 65px;"
                        minlength="2" maxlength="35" required
                    />
                           	<img src="../images/cross.png"
                           	                style="cursor: pointer; vertical-align: 40px;"
                                       	    onclick="
                                       	    document.getElementById('group').value='';
                                       	    document.getElementById('groupId').value='';
                                       	    document.getElementById('deleteGroup').style.visibility = 'hidden';
                                       	    return false;"
                                       	alt="" id = "deleteGroup" />

               <springForm:input type="hidden" id = "groupId" value="${student.gruppa.id}" path="gruppa" readonly="readonly" />


                    <!-- Само модальное окно -->
                    <div id="<c:out value="addGroupWindow"/>" class="modal">






























            <p>

                <center>

                    Введите критерий поиска:
                    <br/>


                    <div class = "myDiv2">

                        <input type = "text" id="searchString">
                        <input type = "button" value = "Поиск" onclick = "sendAjaxGroup();">

                    </div>
                    <br/>



                    <div id = "results">

                        Результаты:

                        <table border = "1 px solid" id="existingGroups" visible = "false">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Название</th>
                            </tr>
                            </thead>

                            <tbody>

                            </tbody>

                        </table>

            <p style='color:red;text-align:center;'>Нет результатов.<br/> Попробуйте смягчить условия поиска</p>


















        </div>


        </center>








        </p>


    </div>





                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id = "csrf" />


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