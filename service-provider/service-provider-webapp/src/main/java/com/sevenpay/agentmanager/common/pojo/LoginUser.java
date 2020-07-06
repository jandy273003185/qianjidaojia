package com.sevenpay.agentmanager.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@Component
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 159781733956357560L;
    private String phoneCode;
    private String userName;
    /**
     * 用户
     */
    private Object userInfo;

    /**
     * 用户token验证(header的键名)
     */
    private String token;

    private String userId;

    private String merchantCustId;

    public String getMerchantCustId() {
        return merchantCustId;
    }

    public void setMerchantCustId(String merchantCustId) {
        this.merchantCustId = merchantCustId;
    }



    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
