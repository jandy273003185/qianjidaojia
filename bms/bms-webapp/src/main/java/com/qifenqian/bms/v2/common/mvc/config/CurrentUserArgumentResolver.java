package com.qifenqian.bms.v2.common.mvc.config;

import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.constant.CacheConstants;
import com.qifenqian.bms.v2.common.util.redis.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiBin
 * @Description: 方法参数解析器
 * @date 2020/4/2 15:50
 */
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(CurrentAccount.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String loginId = request.getHeader("loginId");
        return getCurrentAccount(loginId);
    }

    protected CurrentAccount getCurrentAccount(String loginId) {
        return redisTemplateUtil.getCacheObject(CacheConstants.CURRENT_USER_ID + loginId, CurrentAccount.class);
    }
}
