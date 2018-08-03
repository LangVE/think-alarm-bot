package studygroup.deoksunlee.thinkalarmbot.repository;

import org.springframework.data.repository.CrudRepository;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthentication;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthenticationId;

public interface ApiAuthenticationRepository extends CrudRepository<ApiAuthentication, String> {
    ApiAuthentication findById(ApiAuthenticationId id);
}
