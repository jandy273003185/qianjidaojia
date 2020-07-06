package com.qifenqian.bms.v2.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author LiBin
 * @Description: 配置文件属性
 * @date 2020/4/2 09:38
 */
@Component
@ConfigurationProperties(prefix = "config")
@PropertySource(value = "classpath:/spring.properties")
public class ConfigBean {
    private String pageQuery;

    public String getPageQuery() {
        return pageQuery;
    }

    public void setPageQuery(String pageQuery) {
        this.pageQuery = pageQuery;
    }
}
