package com.heitian.ssm.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class User {
    public interface WithoutPasswordView {
    }

    ;

    public interface WithPasswordView extends WithoutPasswordView {
    }

    ;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(WithPasswordView.class)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUserCreateTime() {
        return userCreateTime;
    }
    
    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    private Long userId;
    private String userPhone;
    private String userName;
    private String userPassword;
    private String userAddress;
    private Date userCreateTime;
    private Date userBirth;//用户生日
    private Token token;

    public String getUserQqId() {
        return userQqId;
    }

    public void setUserQqId(String userQqId) {
        this.userQqId = userQqId;
    }

    public String getUserWechatId() {
        return userWechatId;
    }

    public void setUserWechatId(String userWechatId) {
        this.userWechatId = userWechatId;
    }

    private String userQqId;
    private String userWechatId;

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    private String userHead;


}
