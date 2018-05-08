package studygroup.deoksunlee.thinkalarmbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/sample-html")
    public String sampleHtml() {
        return "sample.html";
    }

    @RequestMapping("/httpclient-test")
    public String httpclientTest() {
        return "httpclient-test.html";
    }
}