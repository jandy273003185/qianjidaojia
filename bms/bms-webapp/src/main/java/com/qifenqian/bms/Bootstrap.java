package com.qifenqian.bms;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableMethodCache(basePackages = { "com.qifenqian.bms.v2" })
@EnableCreateCacheAnnotation
public class Bootstrap extends SpringBootServletInitializer {
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(Bootstrap.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Bootstrap.class, args);
  }
}
