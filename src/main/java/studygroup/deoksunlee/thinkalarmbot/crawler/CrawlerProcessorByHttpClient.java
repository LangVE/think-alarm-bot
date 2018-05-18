package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studygroup.deoksunlee.thinkalarmbot.util.HttpClientUtils;

public class CrawlerProcessorByHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerProcessorByHttpClient.class);

    public static String get(String url) {
        return HttpClientUtils.request(url);
    }

}
