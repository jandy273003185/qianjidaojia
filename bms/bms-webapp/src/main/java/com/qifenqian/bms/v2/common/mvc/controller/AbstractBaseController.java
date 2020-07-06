package com.qifenqian.bms.v2.common.mvc.controller;


import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller公共组件
 */
@ControllerAdvice
public abstract class AbstractBaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 捕获所有异常，进行json封装返回
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public void resolveException(Exception ex, HttpServletResponse response) {
        // TODO 目前不进行异常甄别 所有异常都采取code msg data的形式返回
        response.setStatus(200);
        String code = "500";
        String msg = "请求异常!";
        if (ex instanceof BizException) {
            BizException bex = (BizException) ex;
            if (StringUtils.isNotBlank(bex.getCode())) {
                code = bex.getCode();
            }
            msg = ex.getMessage();
            logger.info("错误提示：" + ex.getMessage(), ex);
        }
        ResultData resultData = ResultData.error(code, msg);
        String responseStr = JSONObject.toJSONString(resultData);
        ajaxJson(responseStr, response);
    }

    /**
     * AJAX输出json，返回null
     **/
    public String ajaxJson(String json, HttpServletResponse response) {
        return ajax(json, "application/json", response);
    }

    /**
     * AJAX输出
     */
    public String ajax(String content, String type, HttpServletResponse response) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error("ajax", e);
        }
        return null;
    }
}
