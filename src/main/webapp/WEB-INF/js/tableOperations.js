function GetCellValues(rowNum, colNum) {
	return document.getElementById('mytable').rows[rowNum].cells[colNum].innerHTML;
}

function setGroup(element) {
		//в хайдден передаем айди
	document.getElementById('groupId').value = 
		GetCellValues(element.parentNode.parentNode.rowIndex,0);	
		//в поле группы передаем ее титле
	document.getElementById('group').value = 
		GetCellValues(element.parentNode.parentNode.rowIndex,1);

            //Делаем видимой картинку для добавления группы
            document.getElementById('deleteGroup').style.visibility = "visible";

            //document.getElementById('close-modal').click();

            //$('#close-modal').trigger('click');

          /*  $("#addGroupWindow").removeClass("in");
            $("#addGroupWindow").css("display","none");*/

            //working
            //$('#addGroupWindow').modal().hide();

       // $('#addGroupWindow').modal('hide');
        //$('#addGroupWindow').modal().close();
        //$("#addGroupWindow.close").click();

//$("#modal .close").click();

 //         $("#modal .close").click()





}

function sortTable(){
 $(document).ready(function(){
   $("#mytable").tablesorter();
 });
}