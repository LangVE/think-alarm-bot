package studygroup.deoksunlee.thinkalarmbot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ThinkAlarmBotController.class)
public class ThinkAlarmBotControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getArrivals() throws Exception {

        mvc.perform(get("/api/check-and-push")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
