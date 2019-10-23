//скрываем результаты
$(document).ready(function (){$('#results').hide()});

//функция для конвертации даты
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


        //отправка AJAX - запроса на сервер
       function sendAjax() {
	   var csrf = $('#csrf').val();
	   var searchString = $('#searchString').val();

	   $.ajax({
			   url : '../../ajaxprocessform',
			   type : 'POST',
			   data : searchString ,
			   headers: { 'X-CSRF-Token' : csrf },
			   contentType: false  // tell jQuery not to set contentType
		}).done( function(data){
				processData(data);
				});
	   }

        //обработка полученного с AJAX JSON'а
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
                            "<td>" + dateConverter(data[i].dateOfBirth,'.') + "</td>" +
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
                }