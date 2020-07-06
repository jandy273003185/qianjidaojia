package com.qifenqian.bms.v2.common.mvc.controller;

import com.qifenqian.bms.v2.common.util.redis.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 基础Controller
 * @date 2020/3/30 13:10
 */
@RestController
@RequestMapping(value = "/v2/")
public class BaseController extends AbstractBaseController {
    @Autowired
    protected RedisTemplateUtil redisTemplateUtil;
}
