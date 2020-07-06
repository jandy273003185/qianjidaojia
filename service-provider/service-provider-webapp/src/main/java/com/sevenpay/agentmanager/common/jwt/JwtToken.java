package com.sevenpay.agentmanager.common.jwt;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * JwtToken:实现shiro的AuthenticationToken接口的类JwtToken
 *
 *
 */
public class JwtToken implements AuthenticationToken{

  private static final long serialVersionUID = -7750011789960132191L;
  
  private String token;

    public JwtToken(String token) {

        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
