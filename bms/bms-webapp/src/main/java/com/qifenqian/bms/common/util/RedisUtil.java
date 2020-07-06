package com.qifenqian.bms.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public final class RedisUtil {

  public static final int RED_ENVELOPE_ROB_DB = 0;
  public static final int RED_ENVELOPE_INFO_DB = 1;
  public static final int OFFLINE_MSG_READ_LOCK_DB = 2;
  public static final int CACHE_MSG_QUEEN_DB = 3;
  public static final int MERCHANT_CASHIER_REF_DB = 4;
  public static final int MERCHANT_DB = 5;

  // Redis服务器IP@
  @Value("${REDIS.IP}")
  private String serverIp;

  // Redis的端口号
  @Value("${REDIS.PORT}")
  private int serverPort;

  // 访问密码（生产）
  @Value("${REDIS.AUTH}")
  private String auth;

  // 可用连接实例的最大数目，默认值为8；
  // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
  private int MAX_ACTIVE = 1024;

  // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
  private int MAX_IDLE = 200;

  // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
  private int MAX_WAIT = 10000;

  private int TIMEOUT = 10000;

  // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
  private boolean TEST_ON_BORROW = true;

  private JedisPool jedisPool = null;

  private static RedisUtil ru = null;

  private static synchronized RedisUtil getInstance() {
    if (ru == null) {
      ru = new RedisUtil();
      return ru;
    } else {
      return ru;
    }
  };

  /*static {
  	ru  = new RedisUtil();
  }*/

  private RedisUtil() {
    initPool();
  }

  private void initPool() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(MAX_ACTIVE);
    config.setMaxIdle(MAX_IDLE);
    config.setMaxWaitMillis(MAX_WAIT);
    config.setTestOnBorrow(TEST_ON_BORROW);
    if ("".equals(auth) || auth == null)
      jedisPool = new JedisPool(config, serverIp, serverPort, TIMEOUT);
    else jedisPool = new JedisPool(config, serverIp, serverPort, TIMEOUT, auth);
  }

  /**
   * 获取Jedis实例
   *
   * @return
   */
  public static synchronized Jedis getJedis() {

    RedisUtil ru = RedisUtil.getInstance();

    try {
      if (ru.jedisPool != null) {
        Jedis resource = ru.jedisPool.getResource();
        return resource;
      } else {
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 释放jedis资源
   *
   * @param jedis
   */
  public static void returnResource(final Jedis jedis) {
    /*if (ru.jedisPool != null) {
    	ru.jedisPool.returnResource(jedis);
    }*/
    if (null != jedis) {
      jedis.close();
    }
  }
}
