package studygroup.deoksunlee.thinkalarmbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import studygroup.deoksunlee.thinkalarmbot.crawler.CrawlerProcessorByHttpClient;
import studygroup.deoksunlee.thinkalarmbot.parser.Parser4Xml;

@RestController
public class TabController {

    private String URL = "http://localhost:8080/sample.xml";

    @PostMapping(value = "/api/check-and-push")
    public String checkAndPush() {
        String result = "{\"code\":200, \"message\":\"success\"}";

        String xml = CrawlerProcessorByHttpClient.get(URL);
        String xpathExpression = "//entrydata[@columnnumber=12]/text";
        String actual = Parser4Xml.parse(xml, xpathExpression);

        return result;
    }

}
