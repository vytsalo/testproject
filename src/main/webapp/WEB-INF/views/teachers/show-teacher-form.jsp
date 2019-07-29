<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


	<link rel="stylesheet" type="text/css" href="<c:url value="\css\style.css" />" />

    <link rel="stylesheet" type="text/css" href="<c:url value="\css\validation.css" />" />


        <!-- Подключение библиотеки jQuery -->
        <script src="<c:url value="\js\jquery-3.4.1.min.js" />"></script>
        <!-- Подключение jQuery плагина Masked Input -->
        <script src="<c:url value="\js\jquery.maskedinput.min.js" />"></script>
        <!-- Подключение методов обработки полей -->
        <script src="<c:url value="\js\mask.js" />"></script>







	<title>Добавление/удаление преподавателя</title>
</head>


<body>

     <div id="signup-form">

        <div id="signup-inner">

        	<div class="clearfix" id="header">

				<img id="signup-icon" src="<c:url value="\images\group.png" />" alt="" />

                    <h1>
				        <c:choose>

                            <c:when test="${update}">

                                 <c:out value="Редактирование преподавателя с ID = ${teacher.id}" />

                            </c:when>

                            <c:otherwise>

                                <c:out value="Добавление преподавателя"/>

                            </c:otherwise>

                        </c:choose>

				    </h1>
            </div>


			<p>Пожалуйста, заполните поля ниже.</p>

            <!-- Поменять лейбли и айдишники -->
            <form method="POST" action="http://localhost:8082/teachers/processform" modelAttribute="teacher" id="send" >

				<input type="text" value="${teacher.id}" name="id" hidden />

                <p>
                <label for="fam">Фамилия *</label>
                <input id="fam" type="text" name="fam" value="${teacher.fam}" minlength="2" maxlength="35" required /><!-- placeholder="Введите фамилию" -->
                </p>

                <p>
                <label for="name">Имя *</label>
                <input id="name" type="text" name="name" value="${teacher.name}" minlength="2" maxlength="35" required />
                </p>

                <p>
                <label for="otch">Отчество *</label>
                <input id="otch" type="text" name="otch" value="${teacher.otch}" minlength="2" maxlength="35" required />
                </p>

                <p>
                <label for="date">Дата рождения *</label>
                <input id="date" type="text" name="date_of_birth" value="${teacher.date_of_birth}" pattern="^(((0[1-9]|[12]\d|3[01])\.(0[13578]|1[02])\.((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\.(0[13456789]|1[012])\.((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\.02\.((19|[2-9]\d)\d{2}))|(29\.02\.((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$" required />
                </p>

                <p>
                <label for="phone">Номер телефона *</label>
                <input id="phone" type="text" name="phone_number" value="${teacher.phone_number}" required />
                </p>

                <p>
                <label for="name">Список групп</label>
                <input id="name" type="text" value="${student.group}" />
                </p>



                <p>

                <input id="submit" type="submit" value="Отправить" /><!-- валю в ксс или куда? -->

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
