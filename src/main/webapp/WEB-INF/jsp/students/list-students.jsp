<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css/auth.css" />
        <link rel="stylesheet" type="text/css" href="../css/pagination.css" />
        <link rel="stylesheet" type="text/css" href="../css/list-table.css" />
        <link rel="stylesheet" type="text/css" href="../css/jquery.modal.min.css" />
        <script src="../js/jquery-3.4.1.min.js"></script>
        <script src="../js/jquery.modal.min.js"></script>

        <script type="text/javascript">

            //формировать html - пагинацию тут
            $(document).ready(function() {

                $('#pagination')
                    .append('<a href="" id = "previous-page" >«</a>')
                    .append('<a href="students?page=1" >1</a>')// from this page -1 to this page +1. if 3 then 1..3 ... 10
                    .append('<a href="students?page=2" >2</a>')
                    .append('<a href="students?page=3">3</a>')
                    .append('<a href="" >...</a>')
                    .append('<a href="students?page=10">10</a>')
                    .append('<a href="" id = "next-page">»</a>');

                const urlParams = new URLSearchParams(window.location.search);
                const pageNumber = urlParams.get('page');
                $("#pagination a:eq(" + pageNumber + ")").attr('class', 'active');

                $('#next-page').attr('href', 'students?page=' + (+pageNumber + 1));
                $('#previous-page').attr('href', 'students?page=' + (+pageNumber - 1));

            });

        </script>

        <title>Список студентов</title>

    </head>


<body style="">

    <jsp:include page="../security/auth.jsp" />

    <c:choose>
        <c:when test="${empty students}">
            <div class="center" >
            <h3>Пусто</h3>
            </div>
        </c:when>
            <c:otherwise>

                <div id = "options-block">
                <h3 style = "color:#808080;">
                    <img src = "../images/testicon/student/9.png" id = "page-logo" />Студенты
                </h3>
<%--
<hr style="border-top: thick double #32a1ce;"/>
--%>
                    <div class="center" >

                        <div class = "table-action" >
                            <a href = "/students/add" >
                                <img src = "../images/plus_button3.png" />
                                <br/>Добавить
                            </a>
                        </div>

                        <br/>
                        <br/>

                        <div class = "table-action">
                            <a href = "/students/search" ><img src = "../images/searchIcon.png" />
                                <br/>Поиск
                            </a>
                        </div>

                            <br/>
                            <br/>

                            <div class = "table-action">
                                <a href = "/students/export" >
                                    <img src = "../images/export.png" />
                                    <br/>Экспорт
                                </a>
                            </div>

                                <br/>
                                <br/>

                                <div class = "table-action">
                                    <a href = "/students/" >
                                        <img src = "../images/import.png" />
                                        <br/>Импорт
                                    </a>
                                </div>

                    </div>
                </div>

                <table class="list-table" >
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
                            <td><c:out value="${((page-1) * 10) + i.index + 1}"/></td>
                                <%--<td><c:out value="${list.id}"/></td>--%>
                            <td><c:out value="${list.fam}"/></td>
                            <td><c:out value="${list.name}"/></td>
                            <td><c:out value="${list.otch}"/></td>
                            <td><fmt:formatDate value="${list.dateOfBirth}" pattern="dd.MM.yyyy"/></td>
                            <td><c:out value="${list.phoneNumber}"/></td>
                            <td>

                             <%--   <a href = "" > &lt;%&ndash;javascript:getGroup()&ndash;%&gt;
                               Группа
                            </a>--%>
                                <a href = "../groups/update/${list.group.id}" >
                                    <c:out value="${list.group.title}"/>
                                </a>
                            </td>



                            <td>


                                <a href="<c:out value="#ex${list.id}"/>" rel="modal:open">Информация</a>
                                </br>
                                <a href = "students/update/${list.id}">Редактировать</a> <%--Редактировать Изменить--%>
                                </br>
                                <a href = "students/delete/${list.id}?pageFrom=${page}">Удалить</a>

                                <!-- Модальное окно -->
                                <div id="<c:out value="ex${list.id}"/>" class="modal">
                                    <p><c:out value="${list.fam} ${list.name} ${list.otch}"/></p>
                                    <p>Дата рождения:
                                        <fmt:formatDate value="${list.dateOfBirth}" pattern="dd.MM.yyyy"/></p>
                                    <p>Телефон: <c:out value="${list.phoneNumber}"/></p>
                                    <p>Группа: <c:out value="${list.group.title}"/></p>
                                </div>

                            </td>


                        </tr>
                    </c:forEach>

                    </tbody>
                </table>








            </c:otherwise>
    </c:choose>



        <div class="center">
            <div id = "pagination"></div>
            <a href = "/"> <h3 color = "#da70d6"> На главную </h3>  </a>
        </div>

</body>
</html>