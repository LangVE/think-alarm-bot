package studygroup.deoksunlee.thinkalarmbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studygroup.deoksunlee.thinkalarmbot.checker.Checker;
import studygroup.deoksunlee.thinkalarmbot.crawler.CrawlerProcessorByHttpClient;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthentication;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthenticationId;
import studygroup.deoksunlee.thinkalarmbot.entity.PushLog;
import studygroup.deoksunlee.thinkalarmbot.parser.Event;
import studygroup.deoksunlee.thinkalarmbot.parser.Events;
import studygroup.deoksunlee.thinkalarmbot.parser.Parser4Xml;
import studygroup.deoksunlee.thinkalarmbot.push.Push4Slack;
import studygroup.deoksunlee.thinkalarmbot.push.SlackChatPostMessageResponse;
import studygroup.deoksunlee.thinkalarmbot.repository.ApiAuthenticationRepository;
import studygroup.deoksunlee.thinkalarmbot.repository.PushLogRepository;

import java.util.Date;
import java.util.List;

@RestController
public class TabController {

    private static final Logger logger = LoggerFactory.getLogger(TabController.class);
    private String URL = "http://localhost:8080/sample.xml";

    @Autowired
    Checker checker;

    @Autowired
    private Push4Slack push4Slack;

    @Autowired
    private ApiAuthenticationRepository apiAuthenticationRepository;

    @Autowired
    PushLogRepository pushLogRepository;

    @PostMapping(value = "/api/check-and-push")
    public String checkAndPush() {
        String result = "{\"code\":200, \"message\":\"success\"}";

        try {
            //crwaler
            String xml = CrawlerProcessorByHttpClient.get(URL);

            //parser
            Events events = Parser4Xml.parseToEvents(xml);

            //checker
            List<String> pushingEventIdList = checker.check(events.getEventIdList());
            List<Event> pushingEventList = events.filter(pushingEventIdList);

            // slack push
            SlackChatPostMessageResponse response = push4Slack.push(pushingEventList);

            savePushLog(pushingEventList, response);

            if (!response.isOk())
                result = String.format("{\"code\":500, \"message\":\"slackAPI 호출 실패(%s)\"}", response.getError());
        } catch (Exception e) {
            String message = "정의되지 않은 오류 발생";
            result = String.format("{\"code\":500, \"message\":\"%s\"}", message);
            logger.error(message, e);
        }

        return result;
    }

    private void savePushLog(List<Event> newEventList, SlackChatPostMessageResponse response) {
        for (Event event : newEventList) {
            PushLog pushLog = new PushLog();
            pushLog.setEventId(event.getEventId());
            pushLog.setEventTitle(event.getEventTitle());
            pushLog.setModDate(new Date());
            pushLog.setModNo(0);
            pushLog.setRegDate(new Date());
            pushLog.setRegNo(0);
            pushLog.setSendYn(response.isOk());
            pushLogRepository.save(pushLog);
        }
    }

    @RequestMapping(value = "/api/jpa-test")
    public String jpaTest() {

        ApiAuthentication apiAuthentication = new ApiAuthentication();
        ApiAuthenticationId apiAuthenticationId = new ApiAuthenticationId();


        apiAuthentication.setToken("test2-token");
        apiAuthentication.setModDate(new Date());
        apiAuthentication.setModNo(1);
        apiAuthentication.setRegDate(new Date());
        apiAuthentication.setRegNo(1);

        apiAuthenticationId.setServiceId("serviceId");
        apiAuthenticationId.setWorkspace("test-workspace");

        apiAuthentication.setId(apiAuthenticationId);

        apiAuthenticationRepository.save(apiAuthentication);

        return "";
    }
}
