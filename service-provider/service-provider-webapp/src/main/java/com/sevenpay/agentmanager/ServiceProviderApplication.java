package com.sevenpay.agentmanager;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableDubbo
@SpringBootApplication
public class ServiceProviderApplication extends SpringBootServletInitializer {
  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(ServiceProviderApplication.class);
  }

  public static void main(String[] args) {

    SpringApplication.run(ServiceProviderApplication.class, args);
  }
}
