//функция для конвертации даты
function dateConverter(date, spliterator) {
            alert(date);
            var monthString = date.substring(0,3);
            var day = date.substring(4,6);
            var year = date.substring(8);
            var month;

            //массив циклом?
            switch(monthString) {
                case 'янв':
                    month = "01";
                    break;
                case 'фев':
                    month = "02";
                    break;
                case 'мар':
                    month = "03";
                    break;
                case 'апр':
                    month = "04";
                    break;
                case 'мая':
                    month = "05";
                    break;
                case 'июн':
                    month = "06";
                    break;
                case 'июл':
                    month = "07";
                    break;
                case 'авг':
                    month = "08";
                    break;
                case 'сен':
                    month = "09";
                    break;
                case 'окт':
                    month = 10;
                    break;
                case 'ноя':
                    month = 11;
                    break;
                case 'дек':
                    month = 12;
                    break;
            }

            return day + spliterator + month + spliterator + year;

        }




function dateConverterForProcess(date) {

    var monthString = date.substring(0,3);

    var day = date.substring(4,6);
    var year = date.substring(8);
    var month;

    if (day<10) day='0' + day;//toString



    //массив циклом?
    switch(monthString) {
        case 'янв':
            month = "01";
            break;
        case 'фев':
            month = "02";
            break;
        case 'мар':
            month = "03";
            break;
        case 'апр':
            month = "04";
            break;
        case 'мая':
            month = "05";
            break;
        case 'июн':
            month = "06";
            break;
        case 'июл':
            month = "07";
            break;
        case 'авг':
            month = "08";
            break;
        case 'сен':
            month = "09";
            break;
        case 'окт':
            month = 10;
            break;
        case 'ноя':
            month = 11;
            break;
        case 'дек':
            month = 12;
            break;
    }

    return  year + "-" + month + "-" + day;

}