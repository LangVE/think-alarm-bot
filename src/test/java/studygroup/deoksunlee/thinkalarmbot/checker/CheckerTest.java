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

    List<PushLog> pushLogs;

    @Autowired
    Checker checker;

    @Autowired
    PushLogRepository pushLogRepository;

    @Test
    public void check() {

        // given
        savePushLog(getPushLogs());
        List<String> eventIdList = Arrays.asList("1", "2", "3");

        // when
        List<String> actualList = checker.check(eventIdList);
        deletePushLog();

        // then
        List<String> expectedList = Arrays.asList("2", "3");
        Assert.assertNotNull(actualList);
        logger.info("actualList : " + actualList);
        logger.info("actualList.size() : " + actualList.size());
        Assert.assertEquals(2, actualList.size());
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void check_push된이력조회되지않은경우() {
        // given
        List<String> eventIdList = Arrays.asList("1", "2", "3");

        // when
        List<String> actualList = checker.check(eventIdList);

        // then
        List<String> expectedList = eventIdList;
        Assert.assertNotNull(actualList);
        logger.info("actualList : " + actualList);
        logger.info("actualList.size() : " + actualList.size());
        Assert.assertEquals(eventIdList.size(), actualList.size());
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void check_실패이력제외() {

        // given
        savePushLog(getFaildPushLogs());
        List<String> eventIdList = Arrays.asList("1");

        // when
        List<String> actualList = checker.check(eventIdList);
        deletePushLog();

        // then
        List<String> expectedList = eventIdList;
        Assert.assertNotNull(actualList);
        logger.info("actualList : " + actualList);
        logger.info("actualList.size() : " + actualList.size());
        Assert.assertEquals(expectedList.size(), actualList.size());
        Assert.assertEquals(expectedList, actualList);
    }

    private void savePushLog(List<PushLog> logs) {
        pushLogs = logs;

        pushLogRepository.saveAll(pushLogs);
    }

    private List<PushLog> getPushLogs() {
        List<PushLog> pushLogs = new ArrayList<>();
        pushLogs.add(createPushLog("1"));
        pushLogs.add(createPushLog("4"));
        return pushLogs;
    }

    private List<PushLog> getFaildPushLogs() {
        final boolean sendFaild = false;
        List<PushLog> pushLogs = new ArrayList<>();
        pushLogs.add(createPushLog("1", sendFaild));
        return pushLogs;
    }

    private void deletePushLog() {
        pushLogRepository.deleteAll(pushLogs);
    }

    private PushLog createPushLog(String eventId) {
        return createPushLog(eventId, true);
    }

    private PushLog createPushLog(String eventId, boolean sendYn) {
        PushLog pushLog = new PushLog();
        pushLog.setEventId(eventId);
        pushLog.setEventTitle("test");
        pushLog.setModDate(new Date());
        pushLog.setModNo(0);
        pushLog.setRegDate(new Date());
        pushLog.setRegNo(0);
        pushLog.setSendYn(sendYn);
        return pushLog;
    }
}
