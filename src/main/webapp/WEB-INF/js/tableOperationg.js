function deleteRow(btn) {
  var row = btn.parentNode.parentNode;
  row.parentNode.removeChild(row);
}

function addRow(btn, id) {
  var row = btn.parentNode.parentNode;
  
  document.getElementById(id).getElementsByTagName("tbody")[0].appendChild(row);
 
  var sec_table = document.getElementById("existingGroups");
    
	var elems = sec_table.getElementsByClassName("jstlGroupsSending");

	for (var i = 0; i<elems.length; i++){
		
			elems[i].removeAttribute("name");
		
	}
  
			
  //удалить аттрибут нейм у тегов
  if (id == 'groupsTable'){
	  btn.addEventListener("click", function(){
	  addRow(this,'existingGroups');
	  }, true);
	btn.innerText = "Удалить";
	
  }
  else{
	  btn.addEventListener("click", function(){
	  addRow(this,'groupsTable');
	  }, true);
	btn.innerText = "Добавить";
  
  }
}

function rewriteHTML(){

		var tableStud = document.getElementById("groupsTable");
		
		var inputs = tableStud.getElementsByClassName("jstlGroupsSending");
		//				0		1
		var fields = ["id", "title"];

		/*    остаток деления на 2

				номер группы
		0 1			0
		2 3			1
		4 5			2

		какая группа

		inputs[j] - порядковый номер инпута - все верно



		* */

				//на следующей итерации будет уже 2
			    for (var j = 0; j < inputs.length; j++) {//+=2
				//инпут 0 найм 0
				//инпут 1 нейм 0
					//alert(inputs[j]);
				//inputs[j%2].setAttribute("name","groups[" + Math.floor(j/2) + "]." + fields[j%2]); //"].id");
				inputs[j].setAttribute("name","groups[" + Math.floor(j/2) + "].id");
				j++;
				inputs[j].setAttribute("name","groups[" + Math.floor(j/2) + "].title");
				//он перезаписывает предыдущее, поэтому ошибка

   }
                //перед нажатием на пост
           
}

function classChange(){
		//получаем таблицу
		var tableStud = document.getElementById("groupsTable");
		//получаем инпуты
		var inputs = tableStud.getElementsByClassName("jstlGroupsExisting");
	
		var i = 0;
	
	while(i<inputs.length){
	
		inputs[i].setAttribute("class","jstlGroupsSending");

	}
	
}



function addRowToTeachersTable(btn, id) {
  var row = btn.parentNode.parentNode;
  document.getElementById(id).getElementsByTagName("tbody")[0].appendChild(row);
  var sec_table = document.getElementById("existingGroups");
  var elems = sec_table.getElementsByClassName("jstlGroupsSending");

	for (var i = 0; i<elems.length; i++){
			elems[i].removeAttribute("name");
	}
	
  
  

			
  //удалить аттрибут нейм у тегов
  if (id == 'teachersTable'){
	  btn.addEventListener("click", function(){
	  addRow(this,'existingTeachers');
	  }, true);
	btn.innerText = "Удалить";
	
  }
  else{
	  btn.addEventListener("click", function(){
	  addRow(this,'teachersTable');
	  }, true);
	btn.innerText = "Добавить";
  
  }
}


function rewriteHTMLTeacher(){
		var tableStud = document.getElementById("teachersTable");

		var inputs = tableStud.getElementsByClassName("jstlTeachersSending");
		
		var fields = ["id", "fam", "name","otch", "date_of_birth","phone_number"];

			    for (var j = 0; j < inputs.length; j++) {

                inputs[j].setAttribute("name","teachers[" + Math.floor(j/6) + "]." + fields[j % 6]);
				

   }
                //перед нажатием на пост
             
}

function classChangeTeacher(){
		var tableStud = document.getElementById("teachersTable");
		var inputs = tableStud.getElementsByClassName("jstlTeachersExisting");
		var i = 0;
	//меняет классы
	while(i<inputs.length)
		inputs[i].setAttribute("class","jstlTeachersSending");
	
}