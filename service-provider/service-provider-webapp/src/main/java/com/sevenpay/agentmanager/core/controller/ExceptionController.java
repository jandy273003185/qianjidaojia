package com.sevenpay.agentmanager.core.controller;

import com.sevenpay.agentmanager.common.constants.ExceptionConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName：ExcptionController
 * Description：重定向接收异常处理
 * Author: LiBin
 * Date：2020/1/2 6:20 下午
 */
@RestController
public class ExceptionController extends AbstractBaseController {

    @Resource
    private HttpServletRequest request;

    /**
     * 重新抛出异常
     */
    @PostMapping("/err/rethrow")
    public void rethrow() throws Exception {
        throw ((Exception) request.getAttribute(ExceptionConstants.EXCEPTION_ATTR_KEY));
    }

}
