package studygroup.deoksunlee.thinkalarmbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}
