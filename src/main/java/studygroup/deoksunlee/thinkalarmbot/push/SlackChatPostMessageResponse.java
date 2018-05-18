package studygroup.deoksunlee.thinkalarmbot.push;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"channel", "ts", "message"})
public class SlackChatPostMessageResponse {
    private boolean ok;

    public boolean isOk() {
        return ok;
    }
}
