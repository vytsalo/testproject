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


<!-- CSS привести в порядок, удалить ненужное, поменять названия -->

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
                                <c:out value="Редактирование группы с ID = ${group.id}" />


                            </c:when>
                            <c:otherwise>
                                <c:out value="Добавление группы"/>
                            </c:otherwise>
                        </c:choose>
				
				    </h1>
            
            </div>
			
			
			<p>Пожалуйста, заполните поля ниже.</p><!-- Пожалуйста исправьте следующие ошибки: -->
            
            <form method="POST" action="http://localhost:8082/groups/processform" modelAttribute="group" id="send">

				<input type="text" value="${group.id}" name="id" hidden />

                <p><!-- Убрать и просто текст сделать? -->
                <label for="title">Название *</label>
                <input id="title" type="text" name="title" value="${group.title}" minlength="3" maxlength="35" required /> <!-- required -->
                </p>

				<p>
                <label for="teachers">Список преподавателей</label>
                <input id="teachers" type="text" value="" />
                </p>
                
                <p>
                <label for="students">Список студентов</label>
                <input id="students" type="text" value="" />
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
