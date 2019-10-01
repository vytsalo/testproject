function deleteRow(btn) {
  var row = btn.parentNode.parentNode;
  row.parentNode.removeChild(row);
}

//добавить строку, работает
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
		
		//порядок везде одинаковый сделать
		var fields = ["id", "title"];

		//почему пропускает через 1
			    for (var j = 0; j < inputs.length; j++) {
				
				inputs[j].setAttribute("name","groups[" + j + "].id");
			
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