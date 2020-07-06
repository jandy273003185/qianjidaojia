package com.qifenqian.bms.v2.common.interceptor;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.biz.system.user.bean.vo.UserVO;
import com.qifenqian.bms.v2.common.constant.CacheConstants;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.token.JWTUtil;
import com.qifenqian.bms.v2.common.util.redis.RedisTemplateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author LiBin
 * @Description: 拦截器
 * @date 2020/4/1 10:36
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    protected static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    protected RedisTemplateUtil redisTemplateUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String loginId = request.getHeader("loginId");
        String token = request.getHeader("token");
        this.checkToken(request, response, token, loginId);
        /**
         * 权限访问地址
         */
        String path = request.getRequestURI();
//        checkAccess(loginId, path);
        return true;
    }

    protected boolean checkToken(HttpServletRequest request, HttpServletResponse response, String token, String loginId) throws IOException {
        if (StringUtils.isBlank(loginId)) {
            throw new BizException("888", "loginId请登录!");
        }
        if (StringUtils.isBlank(token)) {
            throw new BizException("888", "无效空token!");
        }
        UserVO userVo = JWTUtil.unsign(token, UserVO.class);
        if (userVo == null) {
            throw new BizException("888", "无效空token!");
        }
        Long id = userVo.getId();
        if (null == id) {
            throw new BizException("888", "无效空token!");
        }
        if (!loginId.equalsIgnoreCase(String.valueOf(id))) {
            throw new BizException("888", "loginId请登录!");
        }
        LocalDateTime lastLogin = userVo.getLastLoginTime();
        if (lastLogin == null) {
            throw new BizException("888", "用户信息异常!");
        }
        //根据用户登录时间，拿到用户申请Token时的secretKey
        String secretKey = JWTUtil.genSecretKey(lastLogin.atZone(ZoneId.systemDefault()).toInstant());
        try {
            //校验
            JWTUtil.verify(secretKey, token);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
        } catch (TokenExpiredException e) {
            // 允许一段时间有效时间同时返回新的token
            String newToken = JWTUtil.getRefreshToken(secretKey, JWTUtil.decode(token), CacheConstants.CURRENT_USER_TOKEN_EXP);
            if (StringUtils.isBlank(newToken)) {
                logger.error(e.getMessage());
                return false;
            }
            response.addHeader("newToken", newToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 验证权限
     *
     * @param loginId
     * @param path
     * @return
     */
    private boolean checkAccess(String loginId, String path) {
        path = path.replace("/v2","");
        CurrentAccount currentAccount = this.getCurrentAccount(loginId);
        if (currentAccount == null) {
            throw new BizException("888", "checkAccess请重新登录!");
        }
        /**
         * 排除超级管理员
         */
        if ("admin".equals(currentAccount.getAccount()) && 1l == currentAccount.getLoginId()) {
            return true;
        }
        /**
         * 取出当前用户的所有权限集合
         */
        Set<String> resourceUrls = currentAccount.getFunctionSet();
        if (CollectionUtils.isEmpty(resourceUrls)) {
            throw new BizException("888", "checkAccess请重新登录!");
        }
        if (!resourceUrls.contains(path)) {
            throw new BizException("500", "权限不足,请联系管理员!");
        }
        return true;
    }

    protected CurrentAccount getCurrentAccount(String loginId) {
        return redisTemplateUtil.getCacheObject(CacheConstants.CURRENT_USER_ID + loginId, CurrentAccount.class);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        super.postHandle(request, response, handler, modelAndView);
        request.setCharacterEncoding("utf-8");
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            String longId = request.getParameter("loginId");
//            OperateLog operateLog = ((HandlerMethod) handler).getMethodAnnotation(OperateLog.class);
//            if (operateLog != null) {
//                Map<String, String> paramMap = getMap(request.getParameterMap());
//                try {
//                    saveOperateLog(paramMap, longId, operateLog.sourceType(), operateLog.remark());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    logger.error(e.getMessage());
//                }
//            }
        }
    }

    protected void saveOperateLog(Map<String, String> paramMap, String longId, int source, String remark) {

    }

    private Map<String, String> getMap(Map properties) {
        Map<String, String> returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name;
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                continue;
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
}
