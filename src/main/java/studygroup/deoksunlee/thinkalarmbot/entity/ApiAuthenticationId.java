package studygroup.deoksunlee.thinkalarmbot.entity;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ApiAuthenticationId implements Serializable {

    private String serviceId;

    private String token;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
