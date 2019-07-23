<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!---------- CSS ------------>
	<!--
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
	-->

<!-- src= -->
	<!--
	<link rel="stylesheet" type="text/css" href="<c:url value="${pageContext.request.contextPath}/css/style.css" />" />
	-->

<!--	<link rel="stylesheet" type="text/css" href="<spring:url value="/.resources/css/style.css" />" />
-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />





<style type="text/css">
 /*
 * simple Signup Form
 * Created by Oussama Afellad
 * www.premiumfreebies.eu
 * 29/08/2011
 */

 body {
 	background: #FDFDFD;
 }
 body, input, textarea {
 	font: 14px/24px Helvetica, Arial, sans-serif;
 	color: #666;
 }
 input {
 	width: 60%
 }
 form {
 	margin: 30px 0 0 0
 }
 input, textarea {
 	background: none repeat scroll 0 0 #FFFFFF;
 	border: 1px solid #C9C9C9;
 	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.15) inset, -5px -5px 0 0 #F5F5F6, 5px 5px 0 0 #F5F5F6, 5px 0 0 0 #F5F5F6, 0 5px 0 0 #F5F5F6, 5px -5px 0 0 #F5F5F6, -5px 5px 0 0 #F5F5F6;
 	color: #545658;
 	padding: 8px;
 	font-size: 14px;
 	border-radius: 2px 2px 2px 2px;
 }
 #submit {
 	background: url("../images/submit_bg.gif") repeat-x scroll 0 0 transparent;
 	border: 1px solid #B7D6DF;
 	border-radius: 2px 2px 2px 2px;
 	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
 	color: #437182;
 	cursor: pointer;
 	font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
 	font-size: 14px;
 	font-weight: bold;
 	height: auto;
 	padding: 6px 10px;
 	text-shadow: 0 1px 0 #FFFFFF;
 	width: auto;
 }
 #submit:hover {
 	background: url("../images/submit_hover_bg.gif") repeat-x scroll 0 0 transparent;
 	border: 1px solid #9FBAC0;
 	cursor: pointer;
 }
 a {
 	color: #88BBC8;
 	text-decoration: none;
 }
 a:hover {
 	color: #f26525
 }
 #signup-form {
 	width: 510px;
 	margin: 0 auto;
 	margin-top: 50px;
 	margin-bottom: 50px;
 	background: #fff;
 	padding: 40px;
 	border: 10px solid #f2f2f2;
 }
 #signup-icon {
 	float: right;
 	width: 48px;
 	height: 48px;
 }
 h1, h2, h3, h4, h5, h6 {
 	margin: 0;
 	padding: 0;
 	color: #444;
 }
 h1 {
 	float: left;
 	margin: 0 0 30px;
 	font-size: 24px;
 	line-height: 34px;
 }
 h2.secondary {
 	float: left;
 	width: 260px;
 	font-size: 16px;
 	font-weight: normal;
 	color: #999;
 	margin-bottom: 30px;
 	line-height: 26px;
 }
 h3 {
 	margin: 30px 0 0 0
 }
 .clearfix:after {
 	content: ".";
 	display: block;
 	height: 0;
 	clear: both;
 	visibility: hidden;
 }
 .clearfix {
 	display: inline-block
 } /* Hide from IE Mac \*/
 .clearfix {
 	display: block;
 } /* End hide from IE Mac */
 .none {
 	display: none;
 } /* End Clearfix _NO__DOTCOMMA__AFTER__*/

 #header {
 	margin: 0 0 30px 0;
 	border-bottom: 1px solid #efefef;
 }
 #send p {
 	margin-bottom: 20px
 }
 textarea {
 	width: 95%;
 	margin: 0 0 0 2px;
 }
 #required p{
 	font-size:10px;
 }
 #apply {
 	border-top: 1px solid #efefef;
 	margin-top: 30px;
 	padding: 20px 0 0 0;
 }
 #apply ul {
 	margin-bottom: 50px
 }
 form label {
 	display: block;
 	margin-bottom: 5px;
 	font-weight: bold;
 	font-size: 12px;
 }
  </style>

	<title>Добавление/удаление группы</title>
</head>




<body>

    <!--BEGIN #signup-form -->
    <div id="signup-form">

        <!--BEGIN #subscribe-inner -->
        <div id="signup-inner">

        	<div class="clearfix" id="header">


				<img id="signup-icon" src="C:\Users\vasilevvs\Downloads\НАДО ОТКРЫТЬ\ембедеды с пожо\testproject\testform\images\student.png" alt="" />
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
