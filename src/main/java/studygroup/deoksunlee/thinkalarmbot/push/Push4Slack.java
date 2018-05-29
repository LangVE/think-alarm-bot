package studygroup.deoksunlee.thinkalarmbot.push;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import studygroup.deoksunlee.thinkalarmbot.util.HttpClientUtils;

import java.io.IOException;
import java.net.URLEncoder;

public class Push4Slack {

    @Value("${property.slack.token}")
    private static String token;

    @Value("${property.slack.channel-id}")
    private static String channelId;

    public static String getToken() {
        return token;
    }

    public static String getChannelId() {
        return channelId;
    }

    private static final String url = "https://slack.com/api/chat.postMessage?token=%s&channel=%s&text=%s&pretty=1";
//    private static final String token = "xoxp-340813866723-341964544615-340373455409-027937cfd9839e0e9254fd57b449f58e";
//    private static final String channelId = "CAP8PSQMP";


    private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    public static SlackChatPostMessageResponse push(String message) {


        SlackChatPostMessageResponse response = null;
        try {
            String encodeMsg = URLEncoder.encode(message, "utf-8");
            String responseString = HttpClientUtils.request(String.format(url, token, channelId, encodeMsg));

            response = mapper.readValue(responseString, SlackChatPostMessageResponse.class);
        } catch (IOException e) {
            throw new IllegalStateException("응답값 parsing 중 에러발생", e);
        }
        return response;
    }
}
