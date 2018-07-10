package studygroup.deoksunlee.thinkalarmbot.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventsTest {

    @Test
    public void getEventIdList() {
        // given
        Events events = new Events(Parser4XmlTest.getEventList());

        // when
        List<String> actualList = events.getEventIdList();

        // then
        List<String> expectedList = getExpectedList();
        Assert.assertEquals(expectedList.get(0), actualList.get(0));
        Assert.assertEquals(expectedList.get(1), actualList.get(1));
        Assert.assertEquals(expectedList.get(2), actualList.get(2));
        Assert.assertEquals(expectedList.get(3), actualList.get(3));
        Assert.assertEquals(expectedList.get(4), actualList.get(4));
    }

    @Test
    public void filter() {
        // given
        Events events = new Events(Parser4XmlTest.getEventList());
        List<String> pushedEventIdList = Arrays.asList("3864", "3862", "3861", "3845");

        // when
        List<Event> actualList = events.filter(pushedEventIdList);

        // then
        List<Event> expectedList = new ArrayList<>();
        expectedList.add(new Event("3867", "[임직원EVENT]***인터파크VR 임직원 EVENT !!***"));
        Assert.assertEquals(expectedList.get(0).getEventId(), actualList.get(0).getEventId());
        Assert.assertEquals(expectedList.get(0).getEventTitle(), actualList.get(0).getEventTitle());
    }

    private List<String> getExpectedList() {
        return Arrays.asList("3867", "3864", "3862", "3861", "3845");
    }
}
