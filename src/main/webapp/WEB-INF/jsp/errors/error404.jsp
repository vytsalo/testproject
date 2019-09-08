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

    <!-- ОНО. РАБОТАЕТ. НЕ. ТРОГАТЬ. -->
	<link rel="stylesheet" type="text/css" href="<c:url value="\css\style.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="\css\validation.css" />" />


<!-- CSS привести в порядок, удалить ненужное, поменять названия -->

	<title>Добавление/удаление группы</title>

</head>



<!-- JavaScript сообщения при неправильной валидации -->


<body>
<jsp:include page="..\testsecurity\auth.jsp" />
     <div id="signup-form">
        	<div class="clearfix" id="header">
			    <img id="signup-icon" src="<c:url value="\images\group.png" />" alt="" />
                    <h1>
                        Ошибка 404. Страница не найдена!
				    </h1>
            </div>


            <div align = center>

<!--
             <img src="<c:url value="\images\error404.png" />" /> -->
             <img src="<c:url value="\images\error404.png" />" />

			<h2>Пожалуйста, проверьте правильность ввода URL
			    или вернитесь на <a href="http://localhost:8082/">главную страницу</a>.
			</h2<!-- Пожалуйста исправьте следующие ошибки: -->
            </div>

		<div id="required">
		<p>VCPROJECT 2019</p>
		</div>


            </div>



    </div>

</body>
</html>
