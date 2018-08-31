package studygroup.deoksunlee.thinkalarmbot.checker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studygroup.deoksunlee.thinkalarmbot.entity.PushLog;
import studygroup.deoksunlee.thinkalarmbot.repository.PushLogRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class Checker {

    @Autowired
    PushLogRepository pushLogRepository;

    public List<String> check(List<String> eventIdList) {
        List<PushLog> pushLogList = pushLogRepository.findByEventIdIn(eventIdList);

        List<String> resultList = new ArrayList<>();

        if (pushLogList.size() == 0)
            return eventIdList;

        for (String eventId : eventIdList) {
            for (PushLog pushLog : pushLogList) {
                if (!pushLog.getEventId().equals(eventId)) {
                    resultList.add(eventId);
                }
            }
        }

        return resultList;
    }
}
