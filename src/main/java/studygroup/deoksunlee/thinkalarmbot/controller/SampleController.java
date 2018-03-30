package studygroup.deoksunlee.thinkalarmbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/event")
    public String event() {
        return "event.html";
    }

    @RequestMapping("/wviwnotice")
    public String wviwnotice() {
        return "wviwnotice.xml";
    }
}