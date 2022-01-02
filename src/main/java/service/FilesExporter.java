package service;

import entities.Student;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class FilesExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public void exportToExcel(List<Student> studentList, HttpServletResponse response) throws IOException {
        workbook = new XSSFWorkbook();
        setResponseHeader(response, "application/octet-stream", ".xlsx", "Student_");
        writeHeaderLine();
        writeDataLine(studentList);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Students");
        XSSFRow row = sheet.createRow(0);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        createCell(row, 0, "№", cellStyle);
        createCell(row, 1, "ФИО", cellStyle);
        createCell(row, 2, "Дата рождения", cellStyle);
        createCell(row, 3, "Номер телефона", cellStyle);
        createCell(row, 4, "Группа", cellStyle);

    }

        private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle cellStyle) {
            XSSFCell cell = row.createCell(columnIndex);
            sheet.autoSizeColumn(columnIndex);

            if (value instanceof Long)
                cell.setCellValue((Long) value);
            else if (value instanceof Integer)
                cell.setCellValue((Integer) value);
            else if (value instanceof Boolean)
                cell.setCellValue((Boolean) value);
            else cell.setCellValue((String) value);

            cell.setCellStyle(cellStyle);

        }

        private void writeDataLine(List<Student> studentList) {
            int rowIndex = 1;

            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();

            font.setFontHeight(14);

            cellStyle.setFont(font);

            int index = 0;
            for (Student student:studentList) {
                XSSFRow row = sheet.createRow(rowIndex++);
                int columnIndex = 0;

                //todo get rid of ++
                createCell(row, columnIndex++, ++index, cellStyle);
                createCell(row, columnIndex++, student.getFam() + " " + student.getName() + " " + student.getOtch(), cellStyle);
                createCell(row, columnIndex++, new SimpleDateFormat("dd.MM.yyyy").format(student.getDateOfBirth()), cellStyle);
                createCell(row, columnIndex++, student.getPhoneNumber(), cellStyle);
                createCell(row, columnIndex++, student.getGroup().getTitle(), cellStyle);

            }


        }



    private void setResponseHeader(HttpServletResponse response, String contentType, String extension, String prefix) {
        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + timeStamp + extension;

        response.setContentType(contentType);

        String headerKey = "Content-Disposition";

        String headerValue = "attachment; filename =" + fileName;

        response.setHeader(headerKey, headerValue);


    }



}
