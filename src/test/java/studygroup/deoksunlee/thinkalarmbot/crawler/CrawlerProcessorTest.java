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
        String expected = "<!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\"><head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "hello!!!!!!!!!!!!!!!\n" +
                "\n" +
                "</body></html>";
        Assert.assertEquals(expected, actual);
    }
}
