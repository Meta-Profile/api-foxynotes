package com.metaprofile.api.security.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Table(name = "user_login_session")
@Entity
public class UserLoginSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;
    private String token;
    private Integer status = 1;
    private String country;
    private String ip;
    private String agent;
    private String fp;
    private Timestamp createTime = Timestamp.from(Instant.now());
    private Timestamp editTime = Timestamp.from(Instant.now());
    private Timestamp expiredTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getSessionId() {
        return sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Timestamp expiredTime) {
        this.expiredTime = expiredTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public void updateEditTime(){
        this.setEditTime(Timestamp.from(Instant.now()));
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }
}
