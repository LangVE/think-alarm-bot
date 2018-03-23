package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CrawlerProcessor {

    private static WebDriver driver = new ChromeDriver();

    public static String get(String url) {


        //WebDriver driver =  new InternetExplorerDriver();
        driver.get(url);
        String text = driver.getPageSource();//findElement(By.id("hello")).getText();
        System.out.println("text : " + text);
        driver.close();
        System.out.println("driver : " + driver);
        return text;
    }
}
