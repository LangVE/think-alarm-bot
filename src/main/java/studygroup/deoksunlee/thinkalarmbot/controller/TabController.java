package studygroup.deoksunlee.thinkalarmbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studygroup.deoksunlee.thinkalarmbot.checker.Checker;
import studygroup.deoksunlee.thinkalarmbot.crawler.CrawlerProcessorByHttpClient;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthentication;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthenticationId;
import studygroup.deoksunlee.thinkalarmbot.parser.Event;
import studygroup.deoksunlee.thinkalarmbot.parser.Events;
import studygroup.deoksunlee.thinkalarmbot.parser.Parser4Xml;
import studygroup.deoksunlee.thinkalarmbot.push.Push4Slack;
import studygroup.deoksunlee.thinkalarmbot.push.SlackChatPostMessageResponse;
import studygroup.deoksunlee.thinkalarmbot.repository.ApiAuthenticationRepository;

import java.util.Date;
import java.util.List;

@RestController
public class TabController {

    private String URL = "http://localhost:8080/sample.xml";

    @Autowired
    Checker checker;

    @Autowired
    private Push4Slack push4Slack;

    @Autowired
    private ApiAuthenticationRepository apiAuthenticationRepository;

    @PostMapping(value = "/api/check-and-push")
    public String checkAndPush() {
        String result = "{\"code\":200, \"message\":\"success\"}";

        //crwaler
        String xml = CrawlerProcessorByHttpClient.get(URL);

        //parser
        Events events = Parser4Xml.parseToEvents(xml);

        //checker
        List<String> pushedList = checker.check(events.getEventIdList());

        List<Event> newEventList = events.filter(pushedList);

        // slack push
        //String message = String.format("새로운 %s번 이벤트[%s]가 등록되었습니다.", eventNoList, eventTitleList);
        String message = ""; // TODO message 만들지 말고 pusher에게 newEventList 넘겨서 처리해봅시다.
        SlackChatPostMessageResponse response = push4Slack.push(message);

        if (!response.isOk())
            result = String.format("{\"code\":500, \"message\":\"%s\"}", response.getError());

        return result;
    }

    @RequestMapping(value = "/api/jpa-test")
    public String jpaTest() {

        ApiAuthentication apiAuthentication = new ApiAuthentication();
        ApiAuthenticationId apiAuthenticationId = new ApiAuthenticationId();


        apiAuthentication.setWorkspace("workspace");
        apiAuthentication.setModDate(new Date());
        apiAuthentication.setModNo(1);
        apiAuthentication.setRegDate(new Date());
        apiAuthentication.setRegNo(1);

        apiAuthenticationId.setServiceId("serviceId");
        apiAuthenticationId.setToken("token");

        apiAuthentication.setId(apiAuthenticationId);

        apiAuthenticationRepository.save(apiAuthentication);

        return "";
    }
}
