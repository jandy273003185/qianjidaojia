package com.sevenpay.agentmanager.common.utils.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间（秒）
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键
     *            时间（秒） 返回0代表永久
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     *            true存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 键 可传入多个键
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    /***************************JSON格式化后存入Redis*************************************/

    /**
     * 删除缓存
     *
     * @param key
     */
    public boolean delCacheWith(final String key) {
        this.makeRedisKey(key);
        return redisTemplate.delete(key);
    }

    /**
     * @Description:
     * @Param: [key, timeout]
     * key
     * timeout 超时秒
     * @return: boolean
     * 设置成功，返回 1 。
     * 设置失败，返回 0 。
     * 默认60秒锁
     * @Author: LiBin
     * @Date: 2019-08-08 09:48
     */
    public boolean addLock(final String key, Integer timeout) {
        this.makeRedisKey(key);
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        boolean result = operation.setIfAbsent(key, "");
        if (result) {
            if (null == timeout || timeout == 0) {
                timeout = 60;
            }
            this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return result;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值 对象集合等要用fastJosn转成String
     * @return 缓存的对象
     */
    public void setCacheObject(String key, Object value, Long expire) {
        key = this.makeRedisKey(key);
        String redisValue = null;
        if (null == value) {
            return;
        }
        if (value instanceof String) {
            redisValue = (String) value;
        } else {
            String objValue = JSONObject.toJSON(value).toString();
            redisValue = (String) objValue;
        }
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(key, redisValue);
        if (null != expire) {
            this.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public String getCacheObject(String key) {
        key = this.makeRedisKey(key);
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 获取缓存对象：对应类型
     *
     * @param key
     * @param deskClass
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(String key, Class deskClass) {
        key = this.makeRedisKey(key);
        String redisValue = this.getCacheObject(key);
        if (null == redisValue) {
            return null;
        }
        T redisObj = (T) JSONObject.parseObject(redisValue, deskClass);
        return redisObj;
    }

    /***
     * 获取缓存数组对象：对应类型
     * @param key
     * @param deskClass
     * @param <T>
     * @return
     */
    public <T> T getCacheList(String key, Class deskClass) {
        key = this.makeRedisKey(key);
        String redisValue = this.getCacheObject(key);
        if (null == redisValue) {
            return null;
        }
        T redisObj = (T) JSONObject.parseArray(redisValue, deskClass);
        return redisObj;
    }

    /**
     * 自增自减
     * @param key
     * @param delta
     * @return
     */
    public Long setIncrement(String key,Long delta){
        key = this.makeRedisKey(key);
        if (delta == 0) {
            throw new RuntimeException("自增自减参数不能为空！");
        }
        Long count = redisTemplate.opsForValue().increment(key,delta);
        return count;
    }

    /**
     * 构造key
     *
     * @param key
     * @return
     */
    public String makeRedisKey(String key) {
        if (null == key) {
            throw new RuntimeException("key不能为空");
        }
        return key;
    }

}
