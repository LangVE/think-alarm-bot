package studygroup.deoksunlee.thinkalarmbot.repository;

import org.springframework.data.repository.CrudRepository;
import studygroup.deoksunlee.thinkalarmbot.entity.ApiAuthentication;

public interface ApiAuthenticationRepository extends CrudRepository<ApiAuthentication, String> {
}
