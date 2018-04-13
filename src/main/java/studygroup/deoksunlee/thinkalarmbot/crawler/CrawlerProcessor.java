package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class CrawlerProcessor {

    private static WebDriver driver = null;

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
        String text = driver.getPageSource();
        System.out.println("text : " + text);

        return text;
    }

    public static String getByHttpClient(String url) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

    public static String parse(String xml, String expression) throws Exception {

        // XML Document 객체 생성
        InputSource is = new InputSource(new StringReader(xml));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

        // xpath 생성
        XPath xpath = XPathFactory.newInstance().newXPath();

        // NodeList 가져오기 : row 아래에 있는 모든 col1 을 선택
        NodeList cols = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);

        String result = cols.item(0).getTextContent();
        System.out.println(result);

        return result;

    }
}
