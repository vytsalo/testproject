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
	//$(document).ready(function() {});
	

        function convertDateFromNarcomanicFormatToRegular(date,spliterator) {

            var monthString = date.substring(0,3);

            var day = date.substring(4,6);
            var year = date.substring(8);
            var month;

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

       
	   
	   function sendAjaxJson() {  

	   var csrf = $('#csrf').val();
	   //alert(csrf);
	   var searchString = $('#searchString').val();
	   //alert(searchString);
	   	
		//dataType:'text',
		$.ajax({
			   url : '../ajaxprocessform',
			   type : 'POST',
			   data : searchString ,//searchString:searchString
			   headers: { 'X-CSRF-Token' : csrf },
			   processData: false,  // tell jQuery not to process the data
			   contentType: false  // tell jQuery not to set contentType			  
		}).done( function(data){
				//alert(data);
				processJson(data);
				});
	   }
	   
	  //processJson to processData
	  function processJson(data) {
                    //'data' is the json object returned from the server
                  
				  //Очищаем таблицу от предыдущих результатов
                    $('#existingTeachers tbody').empty();
                    //$('#results').empty();
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
                    $("#existingTeachers tbody").append(
                        "<tr>" +
                            "<td>" + data[i].id + "</td>" +
                            "<td>" + data[i].fam + "</td>" +
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].otch + "</td>" +
                            "<td>" + convertDateFromNarcomanicFormatToRegular(data[i].dateOfBirth,'.') + "</td>" +
                            "<td>" + data[i].phoneNumber + "</td>" +
                            "<td><a href = '#'>Добавить</a></td>" +
                        "</tr>"
                    );
                }
                        //показываем таблицу
                        $('#existingTeachers').show();
                //показываем результаты
                    $('#results').show();
                    }
                    //очищаем поле поиска
                    //баг с бад рекуест - нельзя отправлять пустое поле
					//$('#searchString').val('');
                }
	  
	  
	  
	  
	  
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
    </script>
</head>

<body onload="$('#results').hide();">

    <center>

            Введите критерий поиска:
        <br/>
		
		
<div class = "myDiv1">

    <input type = "text" id="searchString">
    <input type = "button" value = "Поиск" onclick = "sendAjaxJson();">
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

            <p style='color:red;text-align:center;'>Нет результатов</p>
        </div>


        </center>

</body>
</html>
