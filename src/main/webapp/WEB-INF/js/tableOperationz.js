//Получаем значение ячейки при нажатии
function GetCellValues(rowNum, colNum) {
	return document.getElementById('existingGroups').rows[rowNum].cells[colNum].innerHTML;
}

//устанавливаем группу
function setGroup(element) {
		//в хайдден передаем айди
	document.getElementById('groupId').value = 
		GetCellValues(element.parentNode.parentNode.rowIndex,0);	
		//в поле группы передаем ее титле
	document.getElementById('group').value = 
		GetCellValues(element.parentNode.parentNode.rowIndex,1);
}

//удаляем строку
function deleteRow(btn) {
  var row = btn.parentNode.parentNode;
  row.parentNode.removeChild(row);
  //вернуть строку в старую таблицу
  //поменять валю кнопки на добавить
}

//добавить строку, работает
function addRow(btn, id) {
  //получаем саму строку, по которой кликнули
  // parent - td(ячейка) parent parent - tr(строка)
  var row = btn.parentNode.parentNode;
  
  //добавляем строку к таблице с таким айди
  document.getElementById(id).appendChild(row);
  
  
  //последнюю строку в таблице изменить, добавить name к аттрибутам
  //или просто добавить класс, и она перерисуется
  
  
  
  /*
  ANOTHER VARIANT
  
   let inputs = document.querySelectorAll("#existingStudents .jstlStudentsSending");
        for(let input of inputs) 
            input.removeAttribute("name");
   
  
  */
  
  /*
  //SHORT VARIANT
  
  var elems = document.getElementById("existingStudents").getElementsByClassName("jstlStudentsSending");
    for (var i = 0; i<elems.length; i++)
            elems[i].removeAttribute("name");
  
  
  */
  
  
  
  
  
  var sec_table = document.getElementById("existingStudents");
    
	var elems = sec_table.getElementsByClassName("jstlStudentsSending");

	for (var i = 0; i<elems.length; i++){
		
			elems[i].removeAttribute("name");
		
	}
  
  alert("Аттрибуты удалены");
			
  
  
  
  //удалить аттрибут нейм у тегов
  if (id == 'studentsTable'){
	  btn.addEventListener("click", function(){
	  addRow(this,'existingStudents');
	  }, true);
	btn.innerText = "Удалить";
	
	
	//тут код для добавления класса к строке
	
	//var newStudentInGroup = document.getElementsByClassName("studentsTable").lastChild;
	//newStudentInGroup.setAttribute("class","jstlStudentsSending");
	
	//сделать роуcreate element
	//existingStudents
	
	
  }
  else{
	  btn.addEventListener("click", function(){
	  addRow(this,'studentsTable');
	  }, true);
	btn.innerText = "Добавить";
  
  }
}

//поменять имя класса на депрекейтед, а потом наоборот
function rewriteHTML(){//remove or add


  //переписать студентов только в этой таблице
		//они же все инпуты
		//присвоить класс - jstlCollectionSending
		
		var tableStud = document.getElementById("studentsTable");
		
		var inputs = tableStud.getElementsByClassName("jstlStudentsSending");
		
		var fields = ["id", "name", "fam", "otch", "date_of_birth","phone_number","gruppa"];

			    for (var j = 0; j < inputs.length; j++) {

                //students[0].id
				inputs[j].setAttribute("name","students[" + Math.floor(j/7) + "]." + fields[j % 7]);
				//if из другой таблицы
				// removeAttribute(name);

   }
                //перед нажатием на пост
                alert("Замена успешна");
}

/*
function rewriteForDeletingStudents(){
	
	var tableRows = document.getElementsByClassName('existingStudents');
	var deletiones = tableRows.getElementsByClassName('jstlStudentsSending');
	
	deletiones.
	
	var tl = tableRows.length;
	
	alert(tl);
	
	tableRows
	
	.removeAttribute("align");
}*/