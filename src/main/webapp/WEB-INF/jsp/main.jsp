<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Группы</title>
</head>
<body align="center">

<jsp:include page="testsecurity\auth.jsp" />

<h2>Главная</h2>

<br/>

<hr/>

<br/>


<!-- Одним ксс стилем сделать -->

<h3>Группа</h3>

<a href='http://localhost:8082/groups/'>Список</a></br>
<a href='http://localhost:8082/groups/add/'>Добавить</a></br>
<a href='http://localhost:8082/groups/update/'>Обновить</a></br>
<a href='http://localhost:8082/groups/delete/'>Удалить</a></br>



<h3>Преподаватели</h3>

<a href='http://localhost:8082/teachers/'>Список</a></br>
<a href='http://localhost:8082/teachers/add/'>Добавить</a></br>
<a href='http://localhost:8082/teachers/update/'>Обновить</a></br>
<a href='http://localhost:8082/teachers/delete/'>Удалить</a></br>


<h3>Студенты</h3>

<a href='http://localhost:8082/students/'>Список</a></br>
<a href='http://localhost:8082/students/add/'>Добавить</a></br>
<!-- <a href='http://localhost:8082/students/processform'>процессформ</a></br> -->
<a href='http://localhost:8082/students/update/'>Обновить</a></br>
<a href='http://localhost:8082/students/delete/'>Удалить</a></br>




<!-- обращение к модели через атрибуты-->


</body>
</html>