//скрываем результаты
//рефактор второго аджакса как resultsTeacher
$(document).ready(function (){$('#studentsResults').hide()});

//        alert(location.href);
//просто передать энтити
        

        //в какой то файл пихнуть, где конфиги, чтобы обращаться к переменной из других файлов
        //вынести в отдельный файл настройки?
        var rootAddress = location.protocol + '//' + location.host;
        var ajaxLinkStudents = rootAddress + '/groups/ajaxstudent';

        //отправка AJAX - запроса на сервер

/**
 * params: id - строки, где храниться запрос(или сама строка)
 * params: url - запроса
 * params:
 *
 */
       function sendAjaxStudent() {
	   var searchStringStudent = $('#searchStringStudent').val();
	   $.ajax({
			   url : ajaxLinkStudents,//studentAjaxProcessForm
			   type : 'POST',
			   data : searchStringStudent ,
			   headers: { 'X-CSRF-Token' : $('#csrf').val() },
			   contentType: false  // tell jQuery not to set contentType
		}).done( function(data){
				processDataStudent(data);
				});
	   }

        //обработка полученного с AJAX JSON'а
        //'data' is the json object returned from the server
/**
 * Сделать параметр сущность - Student и везде будет добавляться студент, в таблицу и в ID
 *
 *
 * params: data - json param getting as a server response(data)
 * params: tableId - таблицы Existing + Entity? куда записываются данные от запроса(jstlStudentsExisting)
 * params: blockResultId (failedBlockResultId) - блока результатов (studentsResults)
 * params: newTableId для присвоения измененным данным(jstl + Entity + Existing)
 * params: tableToAdd таблица для добавления студентов(для отправки формы) (studentsTable)
 * обработка полученного с AJAX JSON'а
 * 'data' is the json object returned from the server
 **/
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
                        $("#studentsResults p").hide();
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
                        //показываем таблицу
                   $('#existingStudents').show();
                //показываем результаты
                   $('#studentsResults').show();

                    }
                }