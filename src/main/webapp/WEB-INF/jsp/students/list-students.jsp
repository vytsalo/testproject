<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css/auth.css" />

        <link rel="stylesheet" type="text/css" href="../css/list-table.css" />
        <link rel="stylesheet" type="text/css" href="../css/jquery.modal.min.css" />
        <script src="../js/jquery-3.4.1.min.js"></script>
        <script src="../js/jquery.modal.min.js"></script>

        <title>Список студентов</title>

    </head>

<body>

    <jsp:include page="../security/auth.jsp" />

    <h2 align="center" color = "#50C878" >Список студентов:</h2>

    <c:choose>
        <c:when test="${empty students}">
            <h3 align="center">Пусто</h3>
        </c:when>
            <c:otherwise>

                <table class="list-table">
                    <thead>
                    <tr>
                        <th>Номер</th>
                            <%--<th>Номер зачетной книжки</th>--%>
                        <th>Фамилия</th>
                        <th>Имя</th>
                        <th>Отчество</th>
                        <th>Дата рождения</th>
                        <th>Телефон</th>
                        <th>Группа</th>
                        <th>Действия</th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach items="${students}" var="list" varStatus="i">
                        <tr>
                            <td><c:out value="${i.index+1}"/></td>
                                <%--<td><c:out value="${list.id}"/></td>--%>
                            <td><c:out value="${list.fam}"/></td>
                            <td><c:out value="${list.name}"/></td>
                            <td><c:out value="${list.otch}"/></td>
                            <td><fmt:formatDate value="${list.dateOfBirth}" pattern="dd.MM.yyyy"/></td>
                            <td><c:out value="${list.phoneNumber}"/></td>
                            <td>
                                <a href = "../groups/update/${list.group.id}" >
                                    <c:out value="${list.group.title}"/>
                                </a>
                            </td>

                            <td>
                                <!-- Модальное окно -->
                                <div id="<c:out value="ex${list.id}"/>" class="modal">
                                    <p><c:out value="${list.fam} ${list.name} ${list.otch}"/></p>
                                    <p>Дата рождения:
                                        <fmt:formatDate value="${list.dateOfBirth}" pattern="dd.MM.yyyy"/></p>
                                    <p>Телефон: <c:out value="${list.phoneNumber}"/></p>
                                    <p>Группа: <c:out value="${list.group.title}"/></p>
                                </div>

                                <a href="<c:out value="#ex${list.id}"/>" rel="modal:open">Информация</a>
                                </br>
                                <a href = "update/${list.id}">Редактировать</a>
                                </br>
                                <a href = "delete/${list.id}">Удалить</a>
                            </td>


                        </tr>
                    </c:forEach>


                    <tr>
                        <td colspan = 8 class="active-td">
                            <a href = "/students/add" >
                                <img src = "../images/plus_button3.png" width = 32 height = 32 />
                                <br/>
                                Добавить
                            </a>
                        </td>
                    </tr>
                    </tbody>
                </table>




            </c:otherwise>
    </c:choose>

<center><a href = "/"> На главную </a></center>
<br/>
<br/>
<br/>
</body>
</html>