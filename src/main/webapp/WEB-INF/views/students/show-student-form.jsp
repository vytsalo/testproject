<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>

<head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <!-- мб без сиюрла можно? -->
        <link rel="stylesheet" type="text/css" href="<c:url value="\css\style.css" />" />

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
            <form method="POST" action="http://localhost:8082/students/processform" modelAttribute="student" id="send">

				<input type="text" value="${student.id}" name="id" hidden />

                <p>
                    <label for="fam">Фамилия *</label><!-- можно ли связываться с неймом, а не с айди -->
                    <input id="fam" type="text" name="fam" value="${student.fam}" required />
                </p>


                <p>
                    <label for="name">Имя *</label>
                    <input id="name" type="text" name="name" value="${student.name}" required />
                </p>

                <p>
                    <label for="otch">Отчество *</label>
                    <input id="otch" type="text" name="otch" value="${student.otch}" required />
                </p>


                    <!-- Маска -->
                    <!-- https://jsfiddle.net/itchief/9faffnof/ -->
                    <!-- Подсветка полей -->
                    <!-- https://medium.com/russian/%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%B0%D1%86%D0%B8%D1%8F-%D1%84%D0%BE%D1%80%D0%BC-%D0%BD%D0%B0-html-%D0%B8-css-c34c982d42a0 -->
                    <!-- с сообщениями -->
                    <!-- https://professorweb.ru/my/html/html5/level2/2_2.php -->
                    <!-- JS Method Validation -->
                    <!-- https://www.w3schools.com/js/tryit.asp?filename=tryjs_validation_number -->

                <p>
                    <label for="date">Дата рождения *</label>
                    <input id="date" type="text" name="date_of_birth" value="${student.date_of_birth}" required />
                </p>

                <p>
                    <label for="phone">Номер телефона *</label>
                    <input id="phone" type="text" name="phone_number" value="${student.phone_number}" required />
                </p>


                <!-- Поиск одной группы -->
                <p>
                    <label for="group">Группа</label>
                    <input id="group" type="text" value="${student.gruppa}" /> <!-- + name="gruppa" -->
                </p>


                <p>
                    <input type="submit" id="submit" value="Отправить" />
                </p>

            </form>

                <div id="required">
                <p>* Поля, обязательные для заполнения</p>
                </div>


            </div>

        </div>

    </div>

</body>
</html>
