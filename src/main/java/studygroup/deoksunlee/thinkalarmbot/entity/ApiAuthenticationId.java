package studygroup.deoksunlee.thinkalarmbot.entity;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ApiAuthenticationId implements Serializable {

    private String serviceId;

    private String workspace;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }
}
