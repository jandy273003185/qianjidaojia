package com.sevenpay.agentmanager.core.service;

import com.sevenpay.agentmanager.common.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName：BaseService
 * Description：TODO
 * Author: LiBin
 * Date：2019/12/27 2:27 下午
 */
public class BaseService {

    @Autowired
    protected RedisUtils redisUtils;
}
