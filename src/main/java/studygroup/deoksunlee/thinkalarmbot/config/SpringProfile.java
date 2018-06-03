package studygroup.deoksunlee.thinkalarmbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SpringProfile {
    @Value("${greeting}")
    private String greeting;

    public String getGreeting() {
        return greeting;
    }
}
