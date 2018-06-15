package studygroup.deoksunlee.thinkalarmbot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = {@Index(name = "idx_push_log_1", columnList = "eventId,sendYn")})
public class PushLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String eventId;
    private String eventTitle;
    private boolean sendYn;
    private Integer modNo;
    private Date modDate;
    private Integer regNo;
    private Date regDate;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public boolean isSendYn() {
        return sendYn;
    }

    public void setSendYn(boolean sendYn) {
        this.sendYn = sendYn;
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

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
