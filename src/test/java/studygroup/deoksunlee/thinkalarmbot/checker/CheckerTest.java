package studygroup.deoksunlee.thinkalarmbot.checker;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studygroup.deoksunlee.thinkalarmbot.entity.PushLog;
import studygroup.deoksunlee.thinkalarmbot.repository.PushLogRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckerTest {

    Logger logger = LoggerFactory.getLogger(CheckerTest.class);

    @Autowired
    Checker checker;

    @Autowired
    PushLogRepository pushLogRepository;

    @Test
    public void check() {

        // given
        savePushLog();
        List<String> eventIdList = Arrays.asList("1", "2", "3");

        // when
        List<String> actualList = checker.check(eventIdList);

        // then
        //List<PushLog> expectedList = getExpectedList();
        List<String> expectedList = Arrays.asList("2", "3");
        Assert.assertNotNull(actualList);
        logger.error("actualList : " + actualList);
        logger.error("actualList.size() : " + actualList.size());
        Assert.assertEquals(2, actualList.size());
        Assert.assertEquals(expectedList, actualList);
    }

    private void savePushLog() {
        pushLogRepository.save(createPushLog("1"));
        pushLogRepository.save(createPushLog("4"));
    }

    private PushLog createPushLog(String eventId) {
        PushLog pushLog = new PushLog();
        pushLog.setEventId(eventId);
        pushLog.setEventTitle("test");
        pushLog.setModDate(new Date());
        pushLog.setModNo(0);
        pushLog.setRegDate(new Date());
        pushLog.setRegNo(0);
        pushLog.setSendYn(true);
        return pushLog;
    }

    private List<PushLog> getExpectedList() {
        List<PushLog> expectedList = new ArrayList<>();
        expectedList.add(createPushLog("2"));
        expectedList.add(createPushLog("3"));
        return expectedList;
    }
}
