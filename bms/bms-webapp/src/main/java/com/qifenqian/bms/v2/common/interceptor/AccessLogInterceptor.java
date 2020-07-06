package com.qifenqian.bms.v2.common.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 访问日志拦截器
 */
@Component
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AccessLogInterceptor.class);

    /**
     * 开始时间
     */
    private static final ThreadLocal<Date> START_TIME = new ThreadLocal<>();

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 记录当前时间
        START_TIME.set(new Date());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        AccessLogAddDTO accessLog = new AccessLogAddDTO();
        try {
            // 初始化 accessLog
            initAccessLog(accessLog, request);
            // 执行插入 accessLog
            //		addAccessLog(accessLog);
            logger.info(accessLog.toString());
            // TODO 提升：暂时不考虑 ELK 的方案。而是基于 MySQL 存储。如果访问日志比较多，需要定期归档。
        } catch (Throwable th) {
            logger.error("[afterCompletion][插入访问日志({}) 发生异常({})", JSON.toJSONString(accessLog),
                    ExceptionUtils.getRootCauseMessage(th));
        } finally {
            clear();
        }
    }

    private void initAccessLog(AccessLogAddDTO accessLog, HttpServletRequest request) {
        // 设置用户编号
        /*accessLog.setUserId(MallUtil.getUserId(request));
        if (accessLog.getUserId() == null) {
            accessLog.setUserId(AccessLogAddDTO.USER_ID_NULL);
        }
        accessLog.setUserType(MallUtil.getUserType(request));*/
       /* // 设置访问结果
        CommonResult<?> result = MallUtil.getCommonResult(request);
        Assert.isTrue(result != null, "result 必须非空");
        accessLog.setErrorCode(result.getCode());
        accessLog.setErrorMessage(result.getMessage());*/
        // 设置其它字段
        accessLog.setTraceId(MallUtil.getTraceId());
        accessLog.setApplicationName(applicationName);
        accessLog.setUri(request.getRequestURI());
        accessLog.setQueryString(HttpUtil.buildQueryString(request));
        accessLog.setMethod(request.getMethod());
        accessLog.setUserAgent(HttpUtil.getUserAgent(request));
        accessLog.setIp(HttpUtil.getIp(request));
        accessLog.setStartTime(START_TIME.get());
        accessLog.setResponseTime((int) (System.currentTimeMillis() - accessLog.getStartTime().getTime())); // 默认响应时间设为
        // 0
    }

    private static void clear() {
        START_TIME.remove();
    }
}
