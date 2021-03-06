<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>

<head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="../../css/style.css" />
        <link rel="stylesheet" type="text/css" href="../../css/validation.css" />
        <link rel="stylesheet" type="text/css" href="../../css/jquery.modal.min.css" />

        <!-- Подключение библиотеки jQuery -->
        <script src="../../js/jquery-3.4.1.min.js"></script>
        <script src="../../js/jquery.modal.min.js"></script>
        <!-- Подключение jQuery плагина Masked Input -->
        <script src="../../js/jquery.maskedinput.min.js"></script>
        <!-- Подключение методов обработки полей -->
        <script src="../../js/mask.js"></script>
        <!-- Операции с таблицами -->
        <script src="../../js/tableOperationg.js"></script>

        <!-- AJAX GROUP -->
        <script src="../../js/ajaxOperationsGroups.js"></script>

        <title>Добавление/обновление преподавателя</title>

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
                               Редактирование преподавателя<br/>с ID = ${teacher.id}
                            </c:when>
                        <c:otherwise>
                            <c:out value="Добавление преподавателя"/>
                            </c:otherwise>
                        </c:choose>
    				</h1>
            </div>


			<p>Пожалуйста, заполните поля ниже.</p>

            <!-- Поменять лейбли и айдишники --> <!-- -ID? -->
            <springForm:form method="POST" action="../../teachers/processform" modelAttribute="teacher" id="send">

				<springForm:input type="hidden" value="${teacher.id}" path="id" />

                <p>
                    <label for="fam">Фамилия *</label><!-- можно ли связываться с неймом, а не с айди -->
                    <!-- привести в вид атрибутов по порядку -->
                    <springForm:input type="text" id="fam" path="fam" value="${teacher.fam}" minlength="2" maxlength="35" required="required" /><!-- рекуиред -->
                    <br/>
                    <springForm:errors path="fam" cssClass="error" />
                </p>

                <p>
                    <label for="name">Имя *</label>
                    <springForm:input type="text" id="name" path="name" value="${teacher.name}" minlength="2" maxlength="35" required="required" />
                    <br/>
                    <springForm:errors path="name" cssClass="error" />
                </p>

                <p>
                    <label for="otch">Отчество *</label>
                    <springForm:input type="text" path="otch" id="otch" value="${teacher.otch}" minlength="2" maxlength="35" required="required" />
                    <br/>
                    <springForm:errors path="otch" cssClass="error" />
                </p>


                <p>
                    <label for="date">Дата рождения *</label>

                    <springForm:input type="date" id="date" path="dateOfBirth" value="${teacher.dateOfBirth}" required="required" min="1950-01-01" max="2005-12-31" />
                    <br/>
                    <springForm:errors path="dateOfBirth" cssClass="error" />
                </p>

                <p>
                    <label for="phone">Номер телефона *</label>
                    <springForm:input type="text" path="phoneNumber" id="phone" value="${teacher.phoneNumber}" required="required" />
                    <br/>
                    <springForm:errors path="phoneNumber" cssClass="error" /> <!-- cssClass="error" -->
                </p>


                <p>

                    <label for="group">Группы:</label>


                <table border = "1 px solid" id="groupsTable">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Действие</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${teacher.groups}" var="tcsList" varStatus="tagStatus">


                        <tr>
                            <td>

                            <springForm:hidden path="groups[${tagStatus.index}].id" value="${tcsList.id}" class = "jstlGroupsSending" />
                            <springForm:hidden path="groups[${tagStatus.index}].title" value="${tcsList.title}" class = "jstlGroupsSending" />




                            <div>${tcsList.id}</div>
							
							</td>


                            <td>${tcsList.title}</td>
                            <td><a href = "#" onclick = "addRow(this,'existingGroups'); rewriteHTML(); return false;">Удалить</a></td>

                        </tr>

                    </c:forEach>




                </tbody>

                </table>











                </p>





<%--




   <p>

                    <label for="groups">Существующие группы:</label>


                <table border = "1 px solid" id="existingGroups">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Действие</th>
                    </tr>
                </thead>
                <tbody>



                    <c:forEach items="${groups}" var="grsList" varStatus="dugStatus">


                        <tr>
                            <td>


								<!-- //// -->

                            <input type = "hidden" value="${grsList.id}" class = "jstlGroupsExisting" />

                            <div>${grsList.id}</div>

							</td>


                            <td>${grsList.title}</td>
                            <td>

							<a href="#" onclick="addRow(this,'groupsTable'); classChange(); rewriteHTML(); return false;">Добавить</a>

							</td>





                        </tr>



                    </c:forEach>




                </tbody>

                </table>











                </p>

--%>





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






                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id = "csrf" />

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