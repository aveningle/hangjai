package com.heitian.ssm.model;

import org.apache.ibatis.type.Alias;

@Alias("Token")
public class Token {
    private long tokenId;
    private String token;

    public long getId() {
        return tokenId;
    }

    public void setId(long tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
