//функция для конвертации даты
function dateConverter(date, spliterator) {

            var monthString = date.substring(0,3);
            var day = date.substring(4,6);
            var year = date.substring(8);
            var month;

            //массив циклом?
            switch(monthString) {
                case 'янв':
                    month = 1;
                    break;
                case 'фев':
                    month = 2;
                    break;
                case 'мар':
                    month = 3;
                    break;
                case 'апр':
                    month = 4;
                    break;
                case 'май':
                    month = 5;
                    break;
                case 'июн':
                    month = 6;
                    break;
                case 'июл':
                    month = 7;
                    break;
                case 'авг':
                    month = 8;
                    break;
                case 'сен':
                    month = 9;
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

    //массив циклом?
    switch(monthString) {
        case 'янв':
            month = 1;
            break;
        case 'фев':
            month = 2;
            break;
        case 'мар':
            month = 3;
            break;
        case 'апр':
            month = 4;
            break;
        case 'май':
            month = 5;
            break;
        case 'июн':
            month = 6;
            break;
        case 'июл':
            month = 7;
            break;
        case 'авг':
            month = 8;
            break;
        case 'сен':
            month = 9;
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