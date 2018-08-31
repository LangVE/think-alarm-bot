package studygroup.deoksunlee.thinkalarmbot.push;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthentication;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthenticationId;
import studygroup.deoksunlee.thinkalarmbot.entity.PushLog;
import studygroup.deoksunlee.thinkalarmbot.parser.Event;
import studygroup.deoksunlee.thinkalarmbot.repository.ApiAuthenticationRepository;
import studygroup.deoksunlee.thinkalarmbot.repository.PushLogRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Push4SlackTest {
    private static final Logger logger = LoggerFactory.getLogger(Push4SlackTest.class);
    private static final String testToken = "test-token";

    @Autowired
    ApiAuthenticationRepository apiAuthenticationRepository;

    @Autowired
    PushLogRepository pushLogRepository;

    @Autowired
    Push4Slack push4Slack;

    @Test
    public void push() {
        // given
        String message = "hello, ut";

        // when
        SlackChatPostMessageResponse response = push4Slack.push(message);
        logger.info("reponse : " + response);

        // then
        Assert.assertTrue(response.isOk());
    }

    @Test
    public void push_eventList() {
        // given
        List<Event> eventList = Arrays.asList(
                new Event("3867", "[임직원EVENT]***인터파크VR 임직원 EVENT !!***"),
                new Event("3864", "★땡처리★ 남프랑스+크로아티아 5국10일 [A380탑승]"));

        // when
        SlackChatPostMessageResponse response = push4Slack.push(eventList);
        logger.info("reponse : " + response);

        // then
        Assert.assertTrue(response.isOk());
    }

    @Test
    public void profileTest() {
        // given

        // when
        String actualChannelId = push4Slack.getChannelId();

        // then
        String expectedChannelId = "CAP8PSQMP";

        Assert.assertEquals(expectedChannelId, actualChannelId);
    }

    @Test
    public void getApiAuthentication() {
        // given
        ApiAuthenticationId id = getApiAuthenticationId();
        saveApiAuthentication(id);

        // when
        ApiAuthentication actual = push4Slack.getApiAuthentication(id);

        // then
        Assert.assertEquals(testToken, actual.getToken());
    }

    @Test
    public void save() {
        // given
        PushLog pushLog = new PushLog();
        String eventId = "test1";
        pushLog.setEventId(eventId);
        pushLog.setEventTitle("test");
        pushLog.setModDate(new Date());
        pushLog.setModNo(0);
        pushLog.setRegDate(new Date());
        pushLog.setRegNo(0);
        pushLog.setSendYn(true);

        // when
        pushLogRepository.save(pushLog);

        List<String> listEventId = Arrays.asList(eventId);
        List<PushLog> expected = pushLogRepository.findByEventIdIn(listEventId);

        // then
        Assert.assertEquals(1, expected.size());
        Assert.assertEquals(eventId, expected.get(0).getEventId());

        pushLogRepository.delete(pushLog);
    }

    private ApiAuthenticationId getApiAuthenticationId() {
        ApiAuthenticationId id = new ApiAuthenticationId();
        id.setWorkspace("test-workspace");
        id.setServiceId("test");
        return id;
    }

    private void saveApiAuthentication(ApiAuthenticationId id) {
        ApiAuthentication apiAuthentication = getApiAuthentication(id);
        apiAuthenticationRepository.save(apiAuthentication);
    }

    private ApiAuthentication getApiAuthentication(ApiAuthenticationId id) {
        ApiAuthentication apiAuthentication = new ApiAuthentication();
        apiAuthentication.setId(id);
        apiAuthentication.setToken(testToken);
        apiAuthentication.setModDate(new Date());
        apiAuthentication.setRegDate(new Date());
        apiAuthentication.setModNo(0);
        apiAuthentication.setRegNo(0);
        return apiAuthentication;
    }
}
