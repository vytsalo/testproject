package controller;

import entities.Group;
import entities.Student;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.EntitiesService;
import service.HumanService;

import java.io.IOException;
import java.text.ParseException;

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
    public String init() throws ParseException, IOException {
       humanService.initDB();
       return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if ("".equals(error)) {//Objects.equals(
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