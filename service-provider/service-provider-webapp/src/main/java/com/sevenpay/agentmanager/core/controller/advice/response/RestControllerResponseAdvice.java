package com.sevenpay.agentmanager.core.controller.advice.response;

import com.sevenpay.agentmanager.core.bean.ResultData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.File;

/**
 * ClassName：RestControllerResponseAdvice
 * Description：DO 统一处理返回值
 * Author: LiBin
 * Date：2020/1/15 5:00 下午
 */
@ControllerAdvice("com.sevenpay.agentmanager")
public class RestControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 对body进行封装处理
        if (body instanceof ResultData || body instanceof File || body instanceof ResponseEntity || body instanceof ModelAndView) {
            return body;
        }
        return ResultData.success(body);

    }
}
