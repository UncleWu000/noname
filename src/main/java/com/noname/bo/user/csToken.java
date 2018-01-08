package com.noname.bo.user;

public class csToken {

    /**
     * 访问token
     */
    private String token;

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * 有效时长
     */
    private Long expireln;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpireln() {
        return expireln;
    }

    public void setExpireln(Long expireln) {
        this.expireln = expireln;
    }
}
