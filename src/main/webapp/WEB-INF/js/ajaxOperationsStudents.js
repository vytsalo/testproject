//скрываем результаты
//resultsTeacher
$(document).ready(function (){$('#studentsResults').hide()});

        //отправка AJAX - запроса на сервер
       function sendAjaxStudent() {
	   var csrf2 = $('#csrf2').val();
	   var searchStringStudent = $('#searchStringStudent').val();

	   //csrf сделать одно поле для всего ксрф
           //попробовать передать ксрф вручную без поля
	   $.ajax({
			   url : 'ajaxstudent',//studentAjaxProcessForm
			   type : 'POST',
			   data : searchStringStudent ,
			   headers: { 'X-CSRF-Token' : csrf2 },
			   contentType: false  // tell jQuery not to set contentType
		}).done( function(data){
				processDataStudent(data);
				});
	   }


	   //add addButtonHandler

        //обработка полученного с AJAX JSON'а
        //'data' is the json object returned from the server
        function processDataStudent(data) {
 				  //Очищаем таблицу от предыдущих результатов
                    $('#existingStudents tbody').empty();
                    //Красными буквами нет результатов
                    if (data.length==0) {
                        //таблицу невидимой делаем
                        $('#existingStudents').hide();
                        //показываем блок
                        $("#studentsResults").show();
                        //показываем надпись, что результатов нет
                        $("#studentsResults p").show();
                    } else {
                // выводим полученные данные в таблицу
                        $("#results p").hide();
                for (var i=0; i< data.length; i++) {
                    //в первую строку запихнуть инпуты
                    $("#existingStudents tbody").append(
                        "<tr>" +
                            "<td>" +

                        "<input type = 'hidden' value= '" +  data[i].id + "' class = 'jstlStudentsExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].fam + "' class = 'jstlStudentsExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].name + "' class = 'jstlStudentsExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].otch + "' class = 'jstlStudentsExisting'/>" +
                        "<input type = 'hidden' value= '" +  dateConverterForProcess(dateConverter(data[i].dateOfBirth, '.')) + "' class = 'jstlStudentsExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].phoneNumber + "' class = 'jstlStudentsExisting'/>" +


                        <!-- не меняется надпись кнопки с добавить на удалить -->
                        data[i].id + "</td>" +
                            "<td>" + data[i].fam + "</td>" +
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].otch + "</td>" +
                            "<td>" + dateConverter(data[i].dateOfBirth,'.') + "</td>" +
                            "<td>" + data[i].phoneNumber + "</td>" +

                            "<td><a href = '#' onclick='addRow(this,\"studentsTable\"); classChange(); rewriteHTML(); return false;'>Добавить</a></td>" +
                        "</tr>"
                    );
                }
                        //показываем таблицу
                   $('#existingStudents').show();
                //показываем результаты
                   $('#studentsResults').show();

                $('#studentsResults p').hide();

                    }
                }