package controller;

import entities.Group;
import entities.Student;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.EntitiesService;
import service.HumanService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

// TODO MAKE A COMMON STYLE OF AUTH JSP PAGES.
// TODO MAKE A TABLES WITH STYLES IN FORMS
// TODO MAKE A TABLES WITH STYLES IN LISTS
// TODO REPLACE ALL LINKS TO C:URL

@Controller
public class MainController {

    //todo auth controller

    @Autowired
    HumanService humanService;

    @Autowired
    private EntitiesService<Group> groupService;

    @Autowired
    private EntitiesService<Student> studentService;

    @Autowired
    private EntitiesService<Teacher> teacherService;


    @GetMapping("/")
    public String mainPage(){
    return "main";
    }

    @GetMapping("/init")
    public String init() throws ParseException {
        //todo в классе UTILS сделать генерацию ФИО и имени
        //todo сделать реальные группы

        Group group1 = new Group("147");
        groupService.add(group1);

        Group group2 = new Group("148");
        groupService.add(group2);

        ArrayList<Group> groupsList = new ArrayList(Arrays.asList(group1, group2));

        Student student1 = new Student("Булаев","Александр", "Николаевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("12.08.1995"), "89061453385", null);
        studentService.add(student1);
        student1.setGroup(group1);
        studentService.update(student1);


        Student student2 = new Student("Вечтомов","Дмитрий", "Викторович",
                new SimpleDateFormat("dd.mm.YYYY").parse("22.03.1995"), "89061453385", null);
        studentService.add(student2);
        student2.setGroup(group1);
        studentService.update(student2);


        Student student3 = new Student("Васильев","Виталий", "Сергеевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("08.11.1994"), "89056984585", null);
        studentService.add(student3);
        student3.setGroup(group1);
        studentService.update(student3);


        Student student4 = new Student("Харламов","Александр", "Алексеевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("22.08.1995"), "89056984585", null);
        studentService.add(student4);
        student4.setGroup(group1);
        studentService.update(student4);



        Student student5 = new Student("Тарасов","Максим", "Алексеевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("23.04.1997"), "89056984585", null);
        studentService.add(student5);
        student5.setGroup(group1);
        studentService.update(student5);



        Teacher teacher1 = new Teacher("Блинков","Юрий", "Анатольевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("08.11.1970"), "89056984585", null);
        teacherService.add(teacher1);
        teacher1.setGroups(groupsList);
        teacherService.update(teacher1);

        Teacher teacher2 = new Teacher("Дудов","Сергей", "Иванович",
                new SimpleDateFormat("dd.mm.YYYY").parse("08.11.1950"), "89056984585", null);
        teacherService.add(teacher2);
        teacher2.setGroups(groupsList);
        teacherService.update(teacher2);


        Teacher teacher3 = new Teacher("Бессонов","Леонид", "Валентинович",
                new SimpleDateFormat("dd.mm.YYYY").parse("08.11.1965"), "89056984585", null);
        teacherService.add(teacher3);
        teacher3.setGroups(groupsList);
        teacherService.update(teacher3);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Неверный логин или пароль");
        }
        return "security/login";
    }

    @GetMapping("/auth")
    public String authPage() {
        return "security/auth";
    }

//попробовать просто ретурн воркбук
/*    @GetMapping("/export")
    //@ResponseBody
    public void export() throws IOException {

        Workbook workbook = humanService.exportToExcel();

        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }*/


//almost working
  /*  @RequestMapping(method = { RequestMethod.GET }, value = { "/export" })
    public ResponseEntity<InputStreamResource> export()
    {
        try
        {
            //Create here your CSV file

            String fileName = "Студенты " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ".xlsx";

            File theCsv = new File(fileName);
            HttpHeaders respHeaders = new HttpHeaders();
            MediaType mediaType = new MediaType("application","vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            //application/x-excel
            //application/x-msexcel

            respHeaders.setContentType(mediaType);
            respHeaders.setContentDispositionFormData("attachment", fileName);
            InputStreamResource isr = new InputStreamResource(new FileInputStream(theCsv));
            return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
        }
        catch (Exception e)
        {
            String messagge = "Error in CSV creation; "+e.getMessage();
            return new ResponseEntity<InputStreamResource>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/


/*
    @GetMapping(value="/export")
    public ResponseEntity<ByteArrayResource> downloadTemplate() throws Exception {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            XSSFWorkbook workbook = (XSSFWorkbook) humanService.exportToExcel(); // creates the workbook
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductTemplate.xlsx");
            //workbook.write(stream);
            //workbook.close();
            workbook.write(stream);
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @GetMapping("/export")
    public HttpStatus export() {

        try {

            humanService.exportToExcel();

            return HttpStatus.OK;

        } catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.UNPROCESSABLE_ENTITY;

        }
    }




    /*
    @GetMapping("/export")
    public void export() throws IOException {
        Workbook wb = humanService.exportToExcel();
        FileSystemResource

        response.setHeader("Content-disposition", "attachment; filename=test.xls");
        wb.write( response.getOutputStream() );
    }*/

/*    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(String param) throws IOException {

        // ...

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }*/


  /*  @RequestMapping(method = { RequestMethod.GET }, value = { "/downloadCsv" })
    public ResponseEntity<InputStreamResource>  downloadCSV()
    {
        try
        {
            String fileName = "test.csv";
            //Create here your CSV file
            File theCsv = new File(fileName);
            HttpHeaders respHeaders = new HttpHeaders();
            MediaType mediaType = new MediaType("text","csv");
            respHeaders.setContentType(mediaType);
            respHeaders.setContentDispositionFormData("attachment", fileName);
            InputStreamResource isr = new InputStreamResource(new FileInputStream(theCsv));
            return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
        }
        catch (Exception e)
        {
            String messagge = "Error in CSV creation; "+e.getMessage();
            logger.error(messagge, e);
            return new ResponseEntity<InputStreamResource>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}