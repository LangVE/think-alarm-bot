package studygroup.deoksunlee.thinkalarmbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import studygroup.deoksunlee.thinkalarmbot.crawler.CrawlerProcessorByHttpClient;
import studygroup.deoksunlee.thinkalarmbot.parser.Parser4Xml;
import studygroup.deoksunlee.thinkalarmbot.push.Push4Slack;
import studygroup.deoksunlee.thinkalarmbot.push.SlackChatPostMessageResponse;

@RestController
public class TabController {

    private String URL = "http://localhost:8080/sample.xml";

    @PostMapping(value = "/api/check-and-push")
    public String checkAndPush() {
        //crwaler
        String result = "{\"code\":200, \"message\":\"success\"}";

        String xml = CrawlerProcessorByHttpClient.get(URL);
        String xpath4EventNo = "//entrydata[@columnnumber=12]/text";
        String eventNo = Parser4Xml.parse(xml, xpath4EventNo);
        String xpath4EventTitle = "//entrydata[@columnnumber=3]/text";
        String eventTitle = Parser4Xml.parse(xml, xpath4EventTitle);

        // slack push
        String message = String.format("새로운 %s번 이벤트[%s]가 등록되었습니다.", eventNo, eventTitle);
        SlackChatPostMessageResponse response = Push4Slack.push(message);

        if (!response.isOk())
            result = String.format("{\"code\":500, \"message\":\"%s\"}", response.getError());

        return result;
    }

}
