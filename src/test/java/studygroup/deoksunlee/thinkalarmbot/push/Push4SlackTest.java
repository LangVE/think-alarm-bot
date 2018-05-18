package studygroup.deoksunlee.thinkalarmbot.push;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Push4SlackTest {
    private static final Logger logger = LoggerFactory.getLogger(Push4SlackTest.class);

    @Test
    public void push() {
        // given
        String message = "hello, ut";

        // when
        SlackChatPostMessageResponse response = Push4Slack.push(message);
        logger.info("reponse : " + response);

        // then
        Assert.assertTrue(response.isOk());
    }
}
