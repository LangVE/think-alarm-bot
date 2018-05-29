package studygroup.deoksunlee.thinkalarmbot.push;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Push4SlackTest {
    private static final Logger logger = LoggerFactory.getLogger(Push4SlackTest.class);

    @Autowired
    Push4Slack push4Slack;

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

    @Test
    public void profileTest() {
        // given

        // when
        String actualToken = Push4Slack.getToken();
        String actualChannelId = Push4Slack.getChannelId();

        // then
        String expectedTocken = "xoxp-340813866723-341964544615-340373455409-027937cfd9839e0e9254fd57b449f58e";
        String expectedChannelId = "CAP8PSQMP";

        Assert.assertEquals(expectedTocken, actualToken);
        Assert.assertEquals(expectedChannelId, actualChannelId);
    }
}
