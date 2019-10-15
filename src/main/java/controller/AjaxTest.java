package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by vasilevvs on 15.10.2019.
 */
@Controller
public class AjaxTest {



    @GetMapping("/ajaxtest")
    public String ajaxPage(Model model, @ModelAttribute("searchString") String searchString){

        model.addAttribute("searchString",searchString);
        return "test/ajax";

    }

//responseBody
    //https://devcolibri.com/spring-mvc-%D0%B8-ajax-%D0%B8%D0%BB%D0%B8-%D0%BA%D0%B0%D0%BA-%D1%8F-%D0%B1%D0%BE%D1%80%D0%BE%D0%BB%D1%81%D1%8F-%D1%81-%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B5%D0%BD%D0%B8%D0%B5%D0%BC-json-%D0%BE%D1%82/
    //http://develnotes.org/article83750#.XaWsaEYzbct

    //JSON

    /*
    <dependency>
    <groupId>org.codehaus.jackson</groupId>
    <artifactId>jackson-mapper-asl</artifactId>
    <version>1.9.13</version>
    </dependency>
    */


   // https://ru.stackoverflow.com/questions/547558/%D0%9A%D0%B0%D0%BA-%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B8%D1%82%D1%8C-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5-%D0%B8%D0%B7-json-%D0%B2-%D0%BA%D0%BE%D0%BD%D1%82%D1%80%D0%BE%D0%BB%D0%BB%D0%B5%D1%80%D0%B5-spring-mvc

    @PostMapping("/ajaxprocessform")
   // @ResponseBody
    public String processAjaxPage(Model model, @ModelAttribute("searchString") String searchString) {
        //нажиматься если она не емпти?

        searchString=searchString+"ывфвщл";

        model.addAttribute("searchStr", searchString);

        return "test/ajax";

    }
}
