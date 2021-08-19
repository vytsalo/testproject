package service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class HumanServiceImpl implements HumanService {


    //все вкладки заполняются
    //лист сотрудники
    public void exportToExcel() throws IOException {
//имя файла + дата
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Студенты");

        //List<Student> studentList = studentService.getList();

        int rownum = 0;
        //todo заголовки

        Cell cell;
        Row row;

        row = sheet.createRow(rownum);

        //todo метод для заголовков, передаем массив названий и применяем стили
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Порядковый номер");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ФИО"); //все вместе


        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Дата рождения");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Номер телефона");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Группа");

        //дата //номер телефона//группа
        //cell.setCellStyle(style);





/*

        for (Student student: studentList){



        }*/




/*

        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
            workbook.write(outputStream);
        }
*/


        try (FileOutputStream outputStream = new
                FileOutputStream("Books.xlsx")) {
            workbook.write(outputStream);
        }

        workbook.close();


        //file
       // return workbook;
    }

}
