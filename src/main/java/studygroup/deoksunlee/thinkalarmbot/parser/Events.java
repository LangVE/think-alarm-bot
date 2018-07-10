package studygroup.deoksunlee.thinkalarmbot.parser;

import java.util.ArrayList;
import java.util.List;

public class Events {
    private List<Event> eventList;

    public Events(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<String> getEventIdList() {
        List<String> result = new ArrayList<>();

        for (Event event : eventList) {
            result.add(event.getEventId());
        }

        return result;
    }

    public List<Event> filter(List<String> pushedEventIdList) {
        List<Event> result = new ArrayList<>();

        for (Event event : eventList) {
            if (!pushedEventIdList.contains(event.getEventId())) {
                result.add(event);
            }
        }
        return result;
    }
}
