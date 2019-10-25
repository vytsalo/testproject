//сделать конвертер только для даты процесс, а потом привод её в нормальный формат
function dateConverter(date, spliterator) {

var i1 = date.indexOf(' ');

var month = date.substring(0, i1);

var day = date.substring(i1+1, date.indexOf(','));

if (day<10) day= '0' + day;

var year = date.substring(date.lastIndexOf(' ')+1);

var months = ["янв","фев","мар","апр","мая","июн","июл","авг","сен","окт","ноя","дек"];

for (var i=0; i<months.length; i++){
if (month == months[i]){
month = i+1;
if (month<10) month= '0' + month;
break;
}
}
//alert(day + spliterator + month + spliterator + year);
return day + spliterator + month + spliterator + year;
}

//25.05.2017
function dateConverterForProcess(date) {
var month = date.substring(0,2);
var day = date.substring(3,5);
var year = date.substring(6);
//alert(year + "-" + month + "-" + day);
return year + "-" + month + "-" + day;
}