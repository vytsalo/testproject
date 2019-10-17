<%--
  Created by IntelliJ IDEA.
  User: vasilevvs
  Date: 15.10.2019
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>AjaxTest</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>

    <script>
        // wait for the DOM to be loaded
        $(document).ready(function() {
            // bind form using ajaxForm
            $('#searchForm').ajaxForm({
                // dataType identifies the expected content type of the server response
                dataType:  'json',

                //when page starts table is invisible

                // success identifies the function to invoke when the server response
                // has been received
                //процесс джейсон - типа оверрайд?


                //при успехе
                success: function processJson(data) {
                    //'data' is the json object returned from the server
                   // alert(data[0]['id'] + " " + data[0].fam + " " + data[0].name + " " + data[0].otch + " " + data[0].phoneNumber + " " + data[0].dateOfBirth);


                    //Очищаем таблицу от предыдущих результатов
                    $('#existingTeachers tbody').empty();


                    //$('#results').empty();



                    //Красными буквами нет результатов
                    if (data.length==0) {

                        //таблицу невидимой делаем
                        $('#existingTeachers').hide();
                        $("#results").append("<p style='color:red;text-align:center;'>Нет результатов</p>")


                    } else {



                // выводим полученные данные в таблицу
                        $("#results p").hide();




                for (var i=0; i< data.length; i++) {
                    $("#existingTeachers tbody").append(
                        "<tr>" +
                            "<td>" + data[i].id + "</td>" +
                            "<td>" + data[i].fam + "</td>" +
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].otch + "</td>" +
                            "<td>" + data[i].dateOfBirth + "</td>" +
                            "<td>" + data[i].phoneNumber + "</td>" +
                            "<td><a href = '#'>Добавить</a></td>" +
                        "</tr>"
                    );

                }


                        $('#existingTeachers').show();

                //показываем результаты
                    $('#results').show();





                    }
                }

                //getTableById().innerHtml -

            });
        });








    </script>
</head>


<!-- закинуть в блок и хайд всего блока -->
<body onload="$('#results').hide();">

<!-- searchForm -->
<!-- modelAttribute="searchString" -->


<form id="searchForm" action="../ajaxprocessform" method="post" >
    <center>

            Введите критерий поиска:
        <br/>
            <input type="text" name="searchString" /><!-- name = field -->

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" modelAttribute="searchString" />

            <input type="submit" value="Поиск" />

        <br/>



        <div id = "results">

            Результаты:

            <table border = "1 px solid" id="existingTeachers" visible = "false">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Дата рождения</th>
                    <th>Телефон</th>
                    <th>Действие</th>
                </tr>
                </thead>

                <tbody>

                </tbody>

            </table>
        </div>


        </center>



    </form>




</body>
</html>
