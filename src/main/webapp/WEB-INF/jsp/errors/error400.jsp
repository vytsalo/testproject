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


	<title>Ошибка 400!</title>

</head>
<body>
<jsp:include page="..\testsecurity\auth.jsp" />
     <div id="signup-form">
        	<div class="clearfix" id="header">
			    <img id="signup-icon" src="<c:url value="\images\group.png" />" alt="" />
                    <h1>
                       Ошибка 400! Что-то пошло не так!
				    </h1>
            </div>


            <div align = center>

             <img src="<c:url value="\images\error400.png" />" />

			<h2>Пожалуйста, проверьте правильность ввода URL
			    или вернитесь на <a href="http://212.193.37.103:8082/">главную страницу</a>.
			</h2<!-- Пожалуйста исправьте следующие ошибки: -->
            </div>

		<div id="required">
		<p>VCPROJECT 2019</p>
		</div>


            </div>



    </div>

</body>
</html>
