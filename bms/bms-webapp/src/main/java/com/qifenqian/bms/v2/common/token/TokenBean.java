package com.qifenqian.bms.v2.common.token;

import java.io.Serializable;

/**
 * @author LiBin
 * @Description: token
 * @date 2020/4/1 14:36
 */
public class TokenBean implements Serializable {
    private String token;

    public TokenBean(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
