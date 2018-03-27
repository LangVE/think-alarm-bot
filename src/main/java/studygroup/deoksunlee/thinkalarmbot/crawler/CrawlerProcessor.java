package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CrawlerProcessor {

    private static WebDriver driver = new ChromeDriver();

    public static String get(String url) {


        //WebDriver driver =  new InternetExplorerDriver();
        driver.get(url);
        String text = driver.findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).getText();
        System.out.println("text : " + text);
        driver.close();
        System.out.println("driver : " + driver);
        return text;
    }
}
