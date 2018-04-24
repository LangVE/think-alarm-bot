package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerProcessorBySeleniumTest {
    private Logger logger = LoggerFactory.getLogger(CrawlerProcessorBySeleniumTest.class);

    @Test
    public void get() {
        //given
        System.setProperty("webdriver.chrome.driver", "D:\\dev\\tool\\selenium\\chrome\\chromedriver_win32\\chromedriver.exe");
        String URL = "http://localhost:8080/";

        // when
        String actual = CrawlerProcessorBySelenium.get(URL);

        // then
        String expected = "3826";
        Assert.assertEquals(expected, actual);
    }

    @Ignore("xpath잘못지정되어실패되는상태")
    @Test
    public void getEventTest() {
        //given
        System.setProperty("webdriver.chrome.driver", "D:\\dev\\tool\\selenium\\chrome\\chromedriver_win32\\chromedriver.exe");
        String URL = "http://localhost:8080/sample-html";

        // when
        String actual = CrawlerProcessorBySelenium.getXpath(URL);

        // then
        String expected = "3826";
        Assert.assertEquals(expected, actual);
    }
}
