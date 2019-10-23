//скрываем результаты
$(document).ready(function (){$('#results').hide()});

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


	   //add addButtonHandler

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
                            "<td>" +

                        "<input type = 'hidden' value= '" +  data[i].id + "' class = 'jstlTeachersExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].fam + "' class = 'jstlTeachersExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].name + "' class = 'jstlTeachersExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].otch + "' class = 'jstlTeachersExisting'/>" +
                        "<input type = 'hidden' value= '" +  dateConverterForProcess(data[i].dateOfBirth,'.') + "' class = 'jstlTeachersExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].phoneNumber + "' class = 'jstlTeachersExisting'/>" +


                        <!-- не меняется надпись кнопки с добавить на удалить -->
                        data[i].id + "</td>" +
                            "<td>" + data[i].fam + "</td>" +
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].otch + "</td>" +
                            "<td>" + dateConverter(data[i].dateOfBirth,'.') + "</td>" +
                            "<td>" + data[i].phoneNumber + "</td>" +



                            "<td><a href = '#' onclick='addRowToTeachersTable(this,\"teachersTable\"); classChangeTeacher(); rewriteHTMLTeacher(); return false;'>Добавить</a></td>" +
                        "</tr>"
                    );
                }
                        //показываем таблицу
                        $('#existingTeachers').show();
                //показываем результаты
                    $('#results').show();
                    }
                }