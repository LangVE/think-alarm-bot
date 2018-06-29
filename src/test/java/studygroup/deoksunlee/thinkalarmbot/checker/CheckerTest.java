package studygroup.deoksunlee.thinkalarmbot.checker;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckerTest {

    @Autowired
    Checker checker;

    @Test
    public void check() {
        // given
        List<String> eventIdList = Arrays.asList("1", "2", "3");

        // when
        List<String> actualList = checker.check(eventIdList);

        // then
        //List<String> expectedList = Arrays.asList("2", "3");
        List<String> expectedList = Arrays.asList("1");
        Assert.assertEquals(expectedList, actualList);
    }
}
