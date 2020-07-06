package com.sevenpay.agentmanager.common.jwt;


import com.sevenpay.agentmanager.common.constants.CacheConstants;
import com.sevenpay.agentmanager.common.constants.ExceptionConstants;
import com.sevenpay.agentmanager.common.utils.redis.RedisUtils;
import com.sevenpay.agentmanager.core.exception.BizException;
import com.sevenpay.external.app.common.util.MD5Security;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * JwtFilter:jwt过滤器来作为shiro的过滤器
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/12
 */
//@Component//这个注入与否影响不大
public class JWTFilter extends BasicHttpAuthenticationFilter {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 执行登录
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
            if (null == redisUtils) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            redisUtils = (RedisUtils) factory.getBean("redisUtils");
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        String md5Token = MD5Security.getMD5String(token + CacheConstants.TOKEN_MD5_SECRET);
        if (!redisUtils.hasKey(CacheConstants.TOKEN_MD5_KEY + md5Token)) {
            redisUtils.delCacheWith(CacheConstants.TOKEN_MD5_KEY + md5Token);
            throw new BizException("401","token已失效!");
        }
        if (token == null) {
            throw new BizException("401","Token不能为空!");
        }
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获

        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            // 发生异常，保存异常栈
            request.setAttribute(ExceptionConstants.EXCEPTION_ATTR_KEY, e);
            try {
                request.getRequestDispatcher(ExceptionConstants.EXCEPTION_URL).forward(request, response);
            } catch (ServletException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }


    /**
     * 对跨域提供支持
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
