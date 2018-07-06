package studygroup.deoksunlee.thinkalarmbot.parser;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Event {
    private String eventId;
    private String eventTitle;

    public Event(String eventId, String eventTitle) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
