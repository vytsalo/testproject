//Код jQuery, установливающий маску для ввода телефона элементу input
//1. После загрузки страницы,  когда все элементы будут доступны выполнить...
$(function(){
  //2. Получить элемент, к которому необходимо добавить маску
 	$("#phone").mask("8(999) 999-9999");

    //$("#date").mask("99.99.9999", {placeholder: "дд.мм.гггг" }); //2019 getCurrent year


    //Дата рождения
    //Сделать 31 12 2019, чтобы дальше нельзя
    //проверка на 31!

    //запрет ввода всего кроме цифр

	//Работает, но не совсем так как надо
	/*
	var num = $("#phone").val();        

	$("#phone").focus().val('').val(num);    
*/
  //как передавать маску без знаков? 
  //метод который удаляет все кроме цифр?

  //$('#phone').setCursorToTextEnd();

  //$('#phone').get(0).setSelectionRange(1, 1);
  //сделать перевод на первую позицию строки




});


