package com.qifenqian.bms.v2.common.mvc.service;

import com.qifenqian.bms.v2.common.util.redis.RedisTemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName：BaseService
 * Description：TODO
 * Author: LiBin
 * Date：2019/12/27 2:27 下午
 */
public class BaseService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected RedisTemplateUtil redisTemplateUtil;
}
