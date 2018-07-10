package studygroup.deoksunlee.thinkalarmbot.parser;

import java.util.List;

public class Events {
    private List<Event> eventList;

    public Events(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Event> getEventList() {
        return eventList;
    }
}
