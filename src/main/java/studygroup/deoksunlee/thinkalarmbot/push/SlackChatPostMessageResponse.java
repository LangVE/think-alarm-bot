package studygroup.deoksunlee.thinkalarmbot.push;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
