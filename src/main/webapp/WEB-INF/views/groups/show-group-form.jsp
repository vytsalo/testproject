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


<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />" />


	<title>Добавление/удаление группы</title>
</head>




<body>

    <!--BEGIN #signup-form -->
    <div id="signup-form">
        
        <!--BEGIN #subscribe-inner -->
        <div id="signup-inner">
        
        	<div class="clearfix" id="header">
        	
        		
				<img id="signup-icon" src="C:\Users\vasilevvs\Downloads\НАДО ОТКРЫТЬ\ембедеды с пожо\testproject\testform\images\group.png" alt="" />
                <h1>
				<c:choose>
  <c:when test="${update}">
    <c:out value="Редактирование группы с ID = ${group.id}" />

    <!-- Загрузка данных из бд -->
  </c:when>
  <c:otherwise>
    <c:out value="Добавление группы"/>
  </c:otherwise>
</c:choose>

				
				
				
				
				</h1>

            
            </div>
			
			
			<p>Пожалуйста, заполните поля ниже.</p>
            
            <form method="POST" action="http://localhost:8082/groups/processform" modelAttribute="group" id="send">

				<input type="text" value="${group.id}" name="id" hidden />

                <p>
                <label for="name">Название *</label>
                <input id="name" type="text" name="title" value="${group.title}" />
                </p>
                
				
				<!-- поменять айдишники -->
                <p>
                <label for="company">Преподаватели</label>
                <input id="company" type="text" value="" />
                </p>
                
                <p>
                <label for="email">Студенты</label>
                <input id="email" type="text" value="" />
                </p>
   
                
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
