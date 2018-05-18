package studygroup.deoksunlee.thinkalarmbot.push;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"channel", "ts", "message"})
public class SlackChatPostMessageResponse {
    private boolean ok;
    private String error;

    public boolean isOk() {
        return ok;
    }

    public String getError() {
        return error;
    }
}
