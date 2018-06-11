package studygroup.deoksunlee.thinkalarmbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studygroup.deoksunlee.thinkalarmbot.crawler.CrawlerProcessorByHttpClient;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthentication;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthenticationId;
import studygroup.deoksunlee.thinkalarmbot.parser.Parser4Xml;
import studygroup.deoksunlee.thinkalarmbot.push.Push4Slack;
import studygroup.deoksunlee.thinkalarmbot.push.SlackChatPostMessageResponse;
import studygroup.deoksunlee.thinkalarmbot.repository.ApiAuthenticationRepository;

import java.util.Date;

@RestController
public class TabController {

    private String URL = "http://localhost:8080/sample.xml";

    @Autowired
    private Push4Slack push4Slack;

    @Autowired
    private ApiAuthenticationRepository apiAuthenticationRepository;

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
        SlackChatPostMessageResponse response = push4Slack.push(message);

        if (!response.isOk())
            result = String.format("{\"code\":500, \"message\":\"%s\"}", response.getError());

        return result;
    }

    @RequestMapping(value = "/api/jpa-test")
    public String jpaTest() {

        ApiAuthenticationId apiAuthenticationId = new ApiAuthenticationId();
        apiAuthenticationId.setServiceId("serviceId");
        apiAuthenticationId.setToken("token");

        ApiAuthentication apiAuthentication = new ApiAuthentication();

        apiAuthentication.setApiAuthenticationid(apiAuthenticationId);
        apiAuthentication.setWorkspace("workspace");
        apiAuthentication.setModDate(new Date());
        apiAuthentication.setModNo(1);
        apiAuthentication.setRegDate(new Date());
        apiAuthentication.setRegNo(1);

        apiAuthenticationRepository.save(apiAuthentication);

        return "";
    }
}
