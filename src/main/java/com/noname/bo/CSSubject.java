package com.noname.bo;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;

import java.util.Date;

public class CSSubject {

    private Integer id;

    private String username;

    private String clientId;

    private Integer channel;

    private Integer channerOrManager;

    private long createDate =  new Date().getTime();

    private  String activityCode;

    private String tokenType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getChannerOrManager() {
        return channerOrManager;
    }

    public void setChannerOrManager(Integer channerOrManager) {
        this.channerOrManager = channerOrManager;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
