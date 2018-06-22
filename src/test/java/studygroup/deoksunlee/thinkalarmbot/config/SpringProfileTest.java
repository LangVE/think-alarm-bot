package studygroup.deoksunlee.thinkalarmbot.config;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringProfileTest {
    @Autowired
    private SpringProfile springProfile;

    @BeforeClass
    public static void setSystemProperties() {
        //System.setProperty("spring.profiles.active", "product");
    }

    @Test
    public void getGreeting() {
        // given

        // when
        String actual = springProfile.getGreeting();

        // then
        String expected = "hello-local";
        Assert.assertEquals(expected, actual);
    }
}
