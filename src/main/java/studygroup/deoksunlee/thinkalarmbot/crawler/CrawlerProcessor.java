package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

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

    public static String getXpath(String url) {
        driver.get(url);
        String text = driver.findElement(By.xpath("//*[@id=\"ViewTable\"]/tbody/tr")).findElements(By.tagName("td")).get(0).getText();
        System.out.println("text : " + text);
        driver.close();
        System.out.println("driver : " + driver);
        return text;
    }

    public static String getXml(String url) {
        driver.get(url);
        String text = driver.findElements(By.tagName("viewentry")).get(0).findElements(By.tagName("entrydata")).get(12).findElement(By.tagName("text")).getText();
        System.out.println("text : " + text);
        List<WebElement> elements = driver.findElements(By.tagName("viewentry"));
        List<WebElement> elements2 = elements.get(0).findElements(By.tagName("entrydata"));
        System.out.println("element2 : " + elements2.toString());

        System.out.println(elements2.get(1).getTagName());
        System.out.println("text : " + elements2.get(1).findElement(By.tagName("text")).getText());
        driver.close();
        System.out.println("driver : " + driver);
        return text;
    }
}
