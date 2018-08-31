package studygroup.deoksunlee.thinkalarmbot.repository;

import org.springframework.data.repository.CrudRepository;
import studygroup.deoksunlee.thinkalarmbot.entity.PushLog;

import java.util.List;


public interface PushLogRepository extends CrudRepository<PushLog, String> {
    List<PushLog> findByEventIdIn(List<String> eventIdList);

    List<PushLog> findByEventIdInAndSendYn(List<String> eventIdList, boolean sendYn);
}
