package com.sevenpay.agentmanager.common.shiro;

import com.sevenpay.agentmanager.core.exception.BizException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qifenqian.app.customer.SalesmanManagerService;
import com.sevenpay.agentmanager.common.jwt.JWTUtil;
import com.sevenpay.agentmanager.common.jwt.JwtToken;


/**
 * 自定义授权
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

    private final static Logger LOG = LoggerFactory.getLogger(MyShiroRealm.class);



    @Reference(version="${app.service.version}")
    SalesmanManagerService salesmanManagerService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 方面用于加密 参数：AuthenticationToken是从表单穿过来封装好的对象
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {

        String token = (String) auth.getCredentials();
        String userId = JWTUtil.getUserId(token);
        String openId = JWTUtil.getOpenId(token);

        if (userId == null) {
        	LOG.error("token无效(空''或者null都不行!)");
            throw new BizException("401","token无效");
        }
        if (openId == null) {
        	LOG.error("token无效(空''或者null都不行!)");
            throw new BizException("401","token无效");
        }
        if (!JWTUtil.verify(token, userId, openId)) {
        	LOG.error("用户名或密码错误(token无效或者与登录者不匹配)!)");
            throw new BizException("401","用户名或密码错误(token无效或者与登录者不匹配)!");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = JWTUtil.getUserId(principals.toString());
        //SysUserInfo userBean = loginManagerService.getSysUserInfo(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;

    }



}
