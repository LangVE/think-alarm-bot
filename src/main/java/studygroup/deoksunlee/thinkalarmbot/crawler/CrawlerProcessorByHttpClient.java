package studygroup.deoksunlee.thinkalarmbot.crawler;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class CrawlerProcessorByHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerProcessorByHttpClient.class);

    public static String get(String url) {

        StringBuffer logBuffer = new StringBuffer();
        try {
            logBuffer.append("url : " + url);

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            // add request header
            request.addHeader("User-Agent", USER_AGENT);
            HttpResponse response = client.execute(request);

            logBuffer.append(", responseStatus : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            logBuffer.append(", response : " + result);

            logger.info(logBuffer.toString());
            return result.toString();
        } catch (Exception e) {
            String message = String.format("Http 요청 처리중 오류 발생(%s)", logBuffer);
            throw new RuntimeException(message, e);
        }
    }
}
