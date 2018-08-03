package studygroup.deoksunlee.thinkalarmbot.push;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthentication;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthenticationId;
import studygroup.deoksunlee.thinkalarmbot.parser.Event;
import studygroup.deoksunlee.thinkalarmbot.repository.ApiAuthenticationRepository;
import studygroup.deoksunlee.thinkalarmbot.util.HttpClientUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class Push4Slack {

    @Value("${property.slack.channel-id}")
    private String channelId;

    @Autowired
    private ApiAuthenticationRepository apiAuthenticationRepository;

    public String getChannelId() {
        return channelId;
    }

    private static final String url = "https://slack.com/api/chat.postMessage?token=%s&channel=%s&text=%s&pretty=1";

    private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    public SlackChatPostMessageResponse push(String message) {
        SlackChatPostMessageResponse response;
        try {
            String encodeMsg = URLEncoder.encode(message, "utf-8");
            String token = getApiAuthentication().getToken();
            String responseString = HttpClientUtils.request(String.format(url, token, channelId, encodeMsg));

            response = mapper.readValue(responseString, SlackChatPostMessageResponse.class);
        } catch (IOException e) {
            throw new IllegalStateException("응답값 parsing 중 에러발생", e);
        }
        return response;
    }

    public SlackChatPostMessageResponse push(List<Event> eventList) {
        String message = getMessage(eventList);
        return push(message);
    }

    private String getMessage(List<Event> eventList) {
        StringBuffer stringBuffer = new StringBuffer();

        for (Event e : eventList) {
            String message = String.format("[%s] %s", e.getEventId(), e.getEventTitle());
            stringBuffer.append(message);
        }

        return stringBuffer.toString();
    }

    private ApiAuthentication getApiAuthentication() {
        ApiAuthenticationId id = getApiAuthenticationId();
        return getApiAuthentication(id);
    }

    private ApiAuthenticationId getApiAuthenticationId() {
        ApiAuthenticationId id = new ApiAuthenticationId();
        id.setWorkspace("think-alarm-bot");
        id.setServiceId("slack");
        return id;
    }

    ApiAuthentication getApiAuthentication(ApiAuthenticationId id) {
        return apiAuthenticationRepository.findById(id);
    }
}
