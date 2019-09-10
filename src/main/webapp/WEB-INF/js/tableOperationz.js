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
  
  if (id == 'studentsTable'){
	  btn.addEventListener("click", function(){
	  addRow(this,'existingGroups');
	  }, true);
	btn.innerText = "Удалить";
  }
  else{
	  btn.addEventListener("click", function(){
	  addRow(this,'studentsTable');
	  }, true);
	btn.innerText = "Добавить";
  
  }
}