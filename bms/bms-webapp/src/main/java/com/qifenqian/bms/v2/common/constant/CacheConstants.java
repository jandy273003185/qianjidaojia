package com.qifenqian.bms.v2.common.constant;

/**
 * @author LiBin
 * @Description: 缓存常量
 * @date 2020/3/30 15:14
 */
public class CacheConstants {
    /**
     * 省市区主KEY
     */
    public final static String REDIS_AREA_KEY = "REDIS_AREA_KEY:";
    public final static String REDIS_AREA_KEY_ALL = "REDIS_AREA_KEY:ALL";
    public final static Long REDIS_AREA_KEY_EXPIRED = 2592000l;

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID_TEMP = "CURRENT_USER_ID_TEMP:";
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID:";
    /**
     * 缓存 token 信息
     */
    public static final String CURRENT_USER_TOKEN = "CURRENT_USER_TOKEN:";
    /**
     * 缓存 token 时长 单位分钟 默认一周
     */
//    public static final Long CURRENT_USER_TOKEN_EXP = 1l;
    public static final Long CURRENT_USER_TOKEN_EXP = 60 * 24 * 7l;
}
