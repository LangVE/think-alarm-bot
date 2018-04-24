package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerProcessorByHttpClientTest {
    private Logger logger = LoggerFactory.getLogger(CrawlerProcessorBySeleniumTest.class);

    @Test
    public void get() {
        //given
        String URL = "http://localhost:8080/httpclient-test";

        // when
        String actual = CrawlerProcessorByHttpClient.get(URL);
        logger.debug("actual : " + actual);

        // then
        String expected = "hello! httpclient";
        Assert.assertEquals(expected, actual);
    }

}
