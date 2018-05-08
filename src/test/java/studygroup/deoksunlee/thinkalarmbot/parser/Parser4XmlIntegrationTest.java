package studygroup.deoksunlee.thinkalarmbot.parser;

import org.junit.Assert;
import org.junit.Test;
import studygroup.deoksunlee.thinkalarmbot.crawler.CrawlerProcessorByHttpClient;

public class Parser4XmlIntegrationTest {

    @Test
    public void parse() {
        //given
        String URL = "http://localhost:8080/sample.xml";

        // when
        String xml = CrawlerProcessorByHttpClient.get(URL);
        String xpathExpression = "//entrydata[@columnnumber=12]/text";
        String actual = Parser4Xml.parse(xml, xpathExpression);

        // then
        String expected = "3867";
        Assert.assertEquals(expected, actual);
    }
}
