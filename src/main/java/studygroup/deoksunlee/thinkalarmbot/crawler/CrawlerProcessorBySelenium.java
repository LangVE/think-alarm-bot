package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerProcessorBySelenium {

    private static Logger logger = LoggerFactory.getLogger(CrawlerProcessorBySelenium.class);
    private static WebDriver driver = new ChromeDriver();

    public static String get(String url) {
        StringBuffer logBuffer = new StringBuffer();

        driver.get(url);
        logBuffer.append("url : " + url);

        String response = driver.getPageSource();
        logBuffer.append(", response : " + response);

        String result = driver.findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).getText();

        driver.close();
        logger.info(logBuffer.toString());

        return result;
    }

    public static String getXpath(String url) {
        StringBuffer logBuffer = new StringBuffer();

        driver.get(url);
        logBuffer.append("url : " + url);

        String response = driver.getPageSource();
        logBuffer.append(", response : " + response);

        String result = driver.findElement(By.xpath("//*[@id=\"ViewTable\"]/tbody/tr")).findElements(By.tagName("td")).get(0).getText();

        driver.close();
        logger.info(logBuffer.toString());

        return result;
    }
}
