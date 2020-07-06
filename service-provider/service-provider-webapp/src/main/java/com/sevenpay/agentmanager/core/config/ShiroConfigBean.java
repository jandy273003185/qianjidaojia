package com.sevenpay.agentmanager.core.config;


import com.sevenpay.agentmanager.common.jwt.JWTFilter;
import com.sevenpay.agentmanager.common.shiro.MyShiroRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 * Shiro 配置bean
 */
@Configuration
public class ShiroConfigBean {

    @Value("${images.relativePaths}")
    private String relativePaths;
    @Value("${images.uri}")
    private String uri;

    @Bean("shirFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put(relativePaths,"anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        //前后端带login登录的或者其他登录的通通放行
        filterChainDefinitionMap.put("/**/login/**", "anon");
        //filterChainDefinitionMap.put("/**/salesman/**", "anon");
        filterChainDefinitionMap.put("/**/loginBinding/**", "anon");
        filterChainDefinitionMap.put("/user/**", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/**.js", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/**/smsSmLogin**/**", "anon");
        filterChainDefinitionMap.put("/**/verifyCode**/**", "anon");
//        filterChainDefinitionMap.put("/**/smsSpLogin**/**", "anon");
        filterChainDefinitionMap.put("/**/financeLogin**/**", "anon");

        /**
         * swagger过滤开始
         */
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/csrf", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/security", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/ui", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        /**
         * swagger过滤结束
         */
        filterChainDefinitionMap.put("/wx/**", "anon");//放行静态资源
        filterChainDefinitionMap.put("/MP_verify_LVtMdY24lPWQfWpf.txt", "anon");//放行静态资源
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }



    /**
     * 注入安全管理器
     * @param myRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(MyShiroRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);

        /*
         * 关闭shiro自带的session，详情见文档
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;

    }

}
