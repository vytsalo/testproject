//скрываем результаты
$(document).ready(function (){$('#results').hide()});


//Groups for teachers
var rootAddress = location.protocol + '//' + location.host;
var ajaxLinkTeachers = rootAddress + '/teachers/ajaxgroup';
alert(ajaxLinkTeachers);
//передать текущую ссылку в методе? сиюрлом валю
// url: ../../webapp/
        //отправка AJAX - запроса на сервер
        //param url
       function sendAjaxGroup() {
	   var csrf = $('#csrf').val();
	   var searchString = $('#searchString').val();//searchStringForTeachers
//

	   //добавить нормальный линк и удалить лишнее
	   $.ajax({
			   url : '../ajaxgroup',
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
                    $('#existingGroups tbody').empty();
                    //Красными буквами нет результатов
                    if (data.length==0) {
                        //таблицу невидимой делаем
                        $('#existingGroups').hide();
                        //показываем блок
                        $("#results").show();
                        //показываем надпись, что результатов нет
                        $("#results p").show();
                    } else {
                // выводим полученные данные в таблицу
                        $("#results p").hide();
                for (var i=0; i< data.length; i++) {
                    //в первую строку запихнуть инпуты
                    $("#existingGroups tbody").append(
                        "<tr>" +
                            "<td>" +

                        "<input type = 'hidden' value= '" +  data[i].id + "' class = 'jstlGroupsExisting'/>" +
                        "<input type = 'hidden' value= '" +  data[i].title + "' class = 'jstlGroupsExisting'/>" +

                        <!-- не меняется надпись кнопки с добавить на удалить -->
                        data[i].id + "</td>" +
                            "<td>" + data[i].title + "</td>" +

                            "<td><a href = '#' onclick='addRow(this,\"groupsTable\"); classChange(); rewriteHTML(); return false;'>Добавить</a></td>" +
                        "</tr>"
                    );
                }
                        //показываем таблицу
                        $('#existingGroups').show();
                //показываем результаты
                    $('#results').show();
                    }
                }