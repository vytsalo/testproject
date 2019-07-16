<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>

<html>
<body>

<!-- if update true - Редактирование -->

<h3>
<c:choose>
  <c:when test="${update}">
    <c:out value="Обновление группы"/>
    <!-- Загрузка данных из бд -->
  </c:when>
  <c:otherwise>
    <c:out value="Добавление группы"/>
  </c:otherwise>
</c:choose>
</h3>


<!-- если с параметром id - то редактирование, если без параметра - то добавление? -->
<!-- ссылаться на контроллер! -->
<!-- modelAttribute как в контроллере! -->
<!-- add без айди -->

<!-- Разные версии для добавления и редактирования(загрузить уже имеющиеся данные) -->
<form method="POST" action="http://localhost:8082/groups/processform" modelAttribute="group">

    <p>Название<input type="text" value="" name="title" /></p>
    <!-- Как реализовать? списки-->

    <!-- Комбобоксом -->
    <p>Преподаватели<input type="text" value="" /></p>
    <p>Студенты<input type="text" value="" /></p>

    <input type="submit" onclick="" style="cursor:pointer"/>

</form>


</body>
</html>