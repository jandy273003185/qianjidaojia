package com.qifenqian.bms.configure;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.qifenqian.bms.configure.security.PrivilegeFilter;
import com.qifenqian.bms.platform.web.admin.filter.GeneralFilter;

@Configuration
public class FilterConfigure {
  /**
   * 权限过滤
   *
   * @return
   */
  @Bean
  public FilterRegistrationBean<PrivilegeFilter> privilegeFilter() {
    PrivilegeFilter privilege = new PrivilegeFilter();
    FilterRegistrationBean<PrivilegeFilter> filter =
        new FilterRegistrationBean<PrivilegeFilter>(privilege);
    filter.setOrder(Integer.MIN_VALUE + 1);
    filter.addUrlPatterns("/*");

    return filter;
  }

  
  @Bean
  public FilterRegistrationBean<CharacterEncodingFilter> encodeingFilter() {
    CharacterEncodingFilter characterFilter = new CharacterEncodingFilter();
    characterFilter.setForceEncoding(true);
    characterFilter.setEncoding("UTF-8");
    FilterRegistrationBean<CharacterEncodingFilter> characterFilterBean =
        new FilterRegistrationBean<CharacterEncodingFilter>(characterFilter);
    characterFilterBean.setOrder(Integer.MIN_VALUE);
    characterFilterBean.addUrlPatterns("/*");
    return characterFilterBean;
  }
  
  @Bean
  public FilterRegistrationBean<GeneralFilter> logFilter() {
    GeneralFilter logFilter = new GeneralFilter();
    FilterRegistrationBean<GeneralFilter> filter =
        new FilterRegistrationBean<GeneralFilter>(logFilter);
    filter.setOrder(Integer.MIN_VALUE);
    filter.addUrlPatterns("/*");
    return filter;
  }

  
}
