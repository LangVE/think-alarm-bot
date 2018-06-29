package studygroup.deoksunlee.thinkalarmbot.checker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studygroup.deoksunlee.thinkalarmbot.repository.PushLogRepository;

import java.util.List;

@Service
public class Checker {

    @Autowired
    PushLogRepository pushLogRepository;

    public List<String> check(List<String> eventIdList) {
        return pushLogRepository.findByEventIdIn(eventIdList);
    }
}
