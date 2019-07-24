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


	<title>Добавление/удаление группы</title>
</head>




<body>

    <!--BEGIN #signup-form -->
    <div id="signup-form">

        <!--BEGIN #subscribe-inner -->
        <div id="signup-inner">

        	<div class="clearfix" id="header">


				<img id="signup-icon" src="<c:url value="\images\group.png" />" alt="" />
                <h1>
				<c:choose>
  <c:when test="${update}">
    <c:out value="Редактирование студента с ID = ${student.id}" />

    <!-- Загрузка данных из бд -->
  </c:when>
  <c:otherwise>
    <c:out value="Добавление студента"/>
  </c:otherwise>
</c:choose>





				</h1>


            </div>


			<p>Пожалуйста, заполните поля ниже.</p>


            <!-- Поменять лейбли и айдишники -->
            <form method="POST" action="http://localhost:8082/students/processform" modelAttribute="student" id="send">

				<input type="text" value="${student.id}" name="id" hidden />

                <p>
                <label for="name">Фамилия *</label>
                <input id="name" type="text" name="fam" value="${student.fam}" />
                </p>


                <p>
                <label for="name">Имя *</label>
                <input id="name" type="text" name="name" value="${student.name}" />
                </p>

                <p>
                <label for="name">Отчество *</label>
                <input id="name" type="text" name="otch" value="${student.otch}" />
                </p>

                <p>
                <label for="name">Дата рождения *</label>
                <input id="name" type="text" name="date_of_birth" value="${student.date_of_birth}" />
                </p>

                <p>
                <label for="name">Номер телефона *</label>
                <input id="name" type="text" name="phone_number" value="${student.phone_number}" />
                </p>


<!--

                <p>
                <label for="name">Группа</label>
                <input id="name" type="text" name="gruppa" value="${student.gruppa}" />
                </p>

-->

                <p>

                <input id="submit" type="submit" style="cursor:pointer" value="Отправить" />

                </p>

            </form>

		<div id="required">
		<p>* Поля, обязательные для заполнения</p>
		</div>


            </div>

        <!--END #signup-inner -->
        </div>

    <!--END #signup-form -->
    </div>

</body>
</html>
