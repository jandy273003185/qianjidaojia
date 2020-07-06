package com.qifenqian.bms.accounting.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/** @author wujian 加载config.properties工具类 */
@Configuration
public class CommonConfig {

  private static Map<String, String> config = new HashMap<String, String>();
  private static CommonConfig common = null;

  public static synchronized CommonConfig getInstance() {
    if (null == common) {
      common = new CommonConfig();
    }
    return common;
  }

  @Value("${VERSION}")
  private String version;

  @Value("${SKIP_FILTER_IP}")
  private String SKIP_FILTER_IP;
  
  public CommonConfig() {

    config.put("VERSION", version);
    config.put("SKIP_FILTER_IP", SKIP_FILTER_IP);

    /*	Properties properties = new Properties();
    try {
    	properties.load(CommonConfig.class.getResourceAsStream("/application.properties"));
    	config = new HashMap<String, String>((Map)properties);
    	logger.info("--------init message done !config map size is ---------"+config.size());
    } catch (IOException e) {
    	logger.error("初始化配置文件config.properties异常", e);
    	throw new RuntimeException("初始化配置文件config.properties异常");
    }*/

  }

  public String getValue(String key) {
    return config.get(key);
  }
}
