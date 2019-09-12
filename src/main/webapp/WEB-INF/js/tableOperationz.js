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

function rewriteHTML(){//remove or add


//document.getElementsByTagName('button')[0].setAttribute('value', 'Buddan');
		

		//document.getElementsByTagName('button')[0].innerText = 'Buddan';
		
		
		//они же все инпуты
		//присвоить класс - jstlCollectionSending
		
		var inputs = document.getElementsByClassName("jstlStudentsSending");
			
		var i = 0;
		//id
		
		alert(inputs[i].getAttribute("name"));
		
		
		//посчитать inputs.length/7 to int
			
		/* 7 записей */
			//for i количество записей
			
			
			//var ml = inputs.length;
			alert(inputs.length);
			
			
			for (var j = 0; j < inputs.length; j++) {

				inputs[j].setAttribute("name","aaaaaaaaaa");
				//name
				inputs[j].setAttribute("name","aaaaaaaaaaa");
				inputs[j].setAttribute("name","ssssssss");
				inputs[j].setAttribute("name","ads");
				inputs[j].setAttribute("name","sdgasdgsd");
				inputs[j].setAttribute("name","wegwgasdg");
				inputs[j].setAttribute("name","poiuft9n");
		
		}

			//working
		//inputs[0].setAttribute("name","gagaga");
		
		/*
		inputs[0].setAttribute("name","students[" + i + "].id");
		//name
		inputs[0].setAttribute("name","students[" + i + "].name");
		inputs[0].setAttribute("name","students[" + i + "].fam");
		inputs[0].setAttribute("name","students[" + i + "].otch");
		inputs[0].setAttribute("name","students[" + i + "].date_of_birth");
		inputs[0].setAttribute("name","students[" + i + "].phone_number");
		inputs[0].setAttribute("name","students[" + i + "].gruppa");
		*/
		
		/*
		var x = document.getElementsByClassName("example");
		
		var i;
		for (i = 0; i < x.length; i++) {
		  x[i].style.backgroundColor = "red";
		}
		*/			



			
		
		/*
		
		var tags = document.getElementsByTagName('input');
		tags[0].innerText = 'Buddan';

		*/



/*
		//первому символу даем[0] и так до конца
		
		//считает пространства имен или по классу
		var tags = document.getElementsByTagName('<form:hidden>');


		Array.prototype.forEach.call(els, function(tags) {
   
			//for i=1 to 7 - св-ва
				
				
			//tags - array
		
			for (var i = 0; i < tags.length; i++){
				for (var j = 0; j < tags.length; j++){
				
				//{индекс i передавать в аттрибутах}
				
				
				
				
				}
			}
	*/	
		
		
			
		
		
		
	//		tags.setAttribute("path", value);


	//		document.getElementById('n_art').setAttribute("rel", choice);



	//		console.log(tags.tagName);
//});
		
		
		
	
}
