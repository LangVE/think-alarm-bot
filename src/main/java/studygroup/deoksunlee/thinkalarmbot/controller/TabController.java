package studygroup.deoksunlee.thinkalarmbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import studygroup.deoksunlee.thinkalarmbot.crawler.CrawlerProcessorByHttpClient;
import studygroup.deoksunlee.thinkalarmbot.parser.Parser4Xml;
import studygroup.deoksunlee.thinkalarmbot.push.Push4Slack;
import studygroup.deoksunlee.thinkalarmbot.push.SlackChatPostMessageResponse;

@RestController
public class TabController {

    private static final Logger logger = LoggerFactory.getLogger(TabController.class);
    private String URL = "http://localhost:8080/sample.xml";

    @PostMapping(value = "/api/check-and-push")
    public String checkAndPush() {
        String result = "{\"code\":200, \"message\":\"success\"}";

        try {
            //crwaler
            String xml = CrawlerProcessorByHttpClient.get(URL);
            String xpath4EventNo = "//entrydata[@columnnumber=12]/text";
            String eventNo = Parser4Xml.parse(xml, xpath4EventNo);
            String xpath4EventTitle = "//entrydata[@columnnumber=3]/text";
            String eventTitle = Parser4Xml.parse(xml, xpath4EventTitle);

            // slack push
            String message = String.format("새로운 %s번 이벤트[%s]가 등록되었습니다.", eventNo, eventTitle);
            SlackChatPostMessageResponse response = Push4Slack.push(message);

            if (!response.isOk())
                result = String.format("{\"code\":500, \"message\":\"slackAPI 호출 실패(%s)\"}", response.getError());
        } catch (Exception e) {
            String message = "정의되지 않은 오류 발생";
            result = String.format("{\"code\":500, \"message\":\"%s\"}", message);
            logger.error(message, e);
        }

        return result;
    }

}
