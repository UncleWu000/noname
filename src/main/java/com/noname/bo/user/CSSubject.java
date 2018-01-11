package com.noname.bo.user;

import com.noname.constant.CSSubjectConst;
import com.noname.util.JWTUtils;
import com.noname.util.JsonUtil;

import java.util.Date;

public class CSSubject {

    private Integer id;

    private String username;

    private String clientId;

    private Integer channel;

    private Integer clientOrManage;

    private long createDate =  new Date().getTime();

    private  String activityCode;

    private String tokenType;

    public CSSubject(){};

    public CSSubject(Integer userId, String usernmae, Integer channel, Integer clientOrManager){
        this.id = userId;
        this.username = usernmae;
        this.channel = channel;
        this.clientOrManage = clientOrManager;
    }

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

    public Integer getClientOrManage() {
        return clientOrManage;
    }

    public void setClientOrManage(Integer clientOrManage) {
        this.clientOrManage = clientOrManage;
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

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    /**
     * 获取token
     * @return
     */
    public String toAccessToken(){
        this.tokenType = CSSubjectConst.type.ACCESS_TOKEN;
        return JWTUtils.createToken(toJson(), CSSubjectConst.time.THREE_DAY_IN_MILLISECOND);
    }


    public String toAccessToken(long millisSecond) {
        this.tokenType = CSSubjectConst.type.ACCESS_TOKEN;
        return JWTUtils.createToken(toJson(), millisSecond);
    }


    /**
     * 获取刷新的token
     * @return
     */
    public String toRefressToken(){
        this.tokenType = CSSubjectConst.type.REFRESS_TOKEN;
        return JWTUtils.createToken(toJson(), CSSubjectConst.time.TEN_DAY_IN_MILLISECOND);
    }

    public String toRefressToken(long millisSecond) {
        this.tokenType = CSSubjectConst.type.REFRESS_TOKEN;
        return JWTUtils.createToken(toJson(), millisSecond);
    }

    public CSToken toToken(){
        CSToken csToken = new CSToken();
        csToken.setToken(toAccessToken());
        csToken.setRefreshToken(toRefressToken());
        csToken.setExpireIn(CSSubjectConst.time.THREE_DAY_IN_MILLISECOND);
        return csToken;
    }

    /**
     * 指定授权token与刷新token的生存时间, 然后生成CSToken对象
     * @param accessTokenMillisSecond
     * @param refreshTokenMillisSecond
     * @return
     */
    public CSToken toToken(long accessTokenMillisSecond, long refreshTokenMillisSecond) {
        CSToken CSToken = new CSToken();
        CSToken.setToken(toAccessToken(accessTokenMillisSecond));
        CSToken.setRefreshToken(toRefressToken(refreshTokenMillisSecond));
        CSToken.setExpireIn(accessTokenMillisSecond);
        return CSToken;
    }




}
