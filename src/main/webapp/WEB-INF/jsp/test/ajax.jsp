<%--
  Created by IntelliJ IDEA.
  User: vasilevvs
  Date: 15.10.2019
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>AjaxTest</title>
    <!-- Файлы в папку -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>

    <script>
//в отдельный файл обработку даты и аджакс

//AjaxHandler.js
//dateConverter


//скрываем результаты
$(document).ready(function (){$('#results').hide()});

$('#results').hide();
        function dateConverter(date, spliterator) {

            var monthString = date.substring(0,3);

            var day = date.substring(4,6);
            var year = date.substring(8);
            var month;

            //массив циклом?
            switch(monthString) {
                case 'янв':
                    month = 1;
                    break;
                case 'фев':
                    month = 2;
                    break;
                case 'мар':
                    month = 3;
                    break;
                case 'апр':
                    month = 4;
                    break;
                case 'май':
                    month = 5;
                    break;
                case 'июн':
                    month = 6;
                    break;
                case 'июл':
                    month = 7;
                    break;
                case 'авг':
                    month = 8;
                    break;
                case 'сен':
                    month = 9;
                    break;
                case 'окт':
                    month = 10;
                    break;
                case 'ноя':
                    month = 11;
                    break;
                case 'дек':
                    month = 12;
                    break;
            }

            return day + spliterator + month + spliterator + year;

        }


function dateConverterForProcess(date) {

    var monthString = date.substring(0,3);

    var day = date.substring(4,6);
    var year = date.substring(8);
    var month;

    //массив циклом?
    switch(monthString) {
        case 'янв':
            month = 1;
            break;
        case 'фев':
            month = 2;
            break;
        case 'мар':
            month = 3;
            break;
        case 'апр':
            month = 4;
            break;
        case 'май':
            month = 5;
            break;
        case 'июн':
            month = 6;
            break;
        case 'июл':
            month = 7;
            break;
        case 'авг':
            month = 8;
            break;
        case 'сен':
            month = 9;
            break;
        case 'окт':
            month = 10;
            break;
        case 'ноя':
            month = 11;
            break;
        case 'дек':
            month = 12;
            break;
    }

    alert(year + "-" + month + "-" + day);

    return  year + "-" + month + "-" + day;

}


       function sendAjax() {

	   var csrf = $('#csrf').val();
	   var searchString = $('#searchString').val();

	   $.ajax({
			   url : '../ajaxprocessform',
			   type : 'POST',
			   data : searchString ,
			   headers: { 'X-CSRF-Token' : csrf },
			   contentType: false  // tell jQuery not to set contentType
		}).done( function(data){
				processData(data);
				});
	   }



        //'data' is the json object returned from the server
        function processData(data) {
 				  //Очищаем таблицу от предыдущих результатов
                    $('#existingTeachers tbody').empty();
                    //Красными буквами нет результатов
                    if (data.length==0) {
                        //таблицу невидимой делаем
                        $('#existingTeachers').hide();
                        //показываем блок
                        $("#results").show();
                        //показываем надпись, что результатов нет
                        $("#results p").show();
                    } else {
                // выводим полученные данные в таблицу
                        $("#results p").hide();
                for (var i=0; i< data.length; i++) {
                    //в первую строку запихнуть инпуты
                    $("#existingTeachers tbody").append(
                        "<tr>" +
                            "<td>" + data[i].id + "</td>" +
                            "<td>" + data[i].fam + "</td>" +
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].otch + "</td>" +
                            "<td>" + dateConverterForProcess(data[i].dateOfBirth) + "</td>" +
                            "<td>" + data[i].phoneNumber + "</td>" +
                            "<td><a href = '#' >Добавить</a></td>" +
                        "</tr>"
                    );
                }
                        //показываем таблицу
                        $('#existingTeachers').show();
                //показываем результаты
                    $('#results').show();
                    }
                }

    </script>
</head>

<body>

    <center>

            Введите критерий поиска:
        <br/>
		
		
<div class = "myDiv1">

    <input type = "text" id="searchString">
    <input type = "button" value = "Поиск" onclick = "sendAjax();">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id = "csrf" />

</div>
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

            <p style='color:red;text-align:center;'>Нет результатов.<br/> Попробуйте смягчить условия поиска</p>
        </div>


        </center>

</body>
</html>
