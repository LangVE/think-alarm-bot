package studygroup.deoksunlee.thinkalarmbot.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class ApiAuthentication {

    @EmbeddedId
    private ApiAuthenticationId id;

    private String token;

    private Integer modNo;

    private Date modDate;

    private Integer regNo;

    private Date regDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getModNo() {
        return modNo;
    }

    public void setModNo(Integer modNo) {
        this.modNo = modNo;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Integer getRegNo() {
        return regNo;
    }

    public void setRegNo(Integer regNo) {
        this.regNo = regNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public ApiAuthenticationId getId() {
        return id;
    }

    public void setId(ApiAuthenticationId id) {
        this.id = id;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }


}
