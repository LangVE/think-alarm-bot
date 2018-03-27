package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class CrawlerProcessorTest {

    private WebDriver driver;

    @Test
    public void get() {
        //given
        System.setProperty("webdriver.chrome.driver", "D:\\dev\\tool\\selenium\\chrome\\chromedriver_win32\\chromedriver.exe");
        String URL = "http://localhost:8080/";

        // when
        String actual = CrawlerProcessor.get(URL);

        // then
        String expected = "3826";
        Assert.assertEquals(expected, actual);
    }
}
