package studygroup.deoksunlee.thinkalarmbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThinkAlarmBotController {
    private static final Logger logger = LoggerFactory.getLogger(ThinkAlarmBotController.class);

    @RequestMapping("/api/check-and-push")
    @ResponseBody
    public String checkAndPush() {
        logger.info("checkAndPush!!");
        return "{\"code\":200, \"message\":\"success\"}";
    }
}
