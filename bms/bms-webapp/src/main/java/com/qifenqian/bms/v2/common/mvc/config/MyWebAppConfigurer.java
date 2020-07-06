package com.qifenqian.bms.v2.common.mvc.config;

import com.qifenqian.bms.v2.common.interceptor.AccessLogInterceptor;
import com.qifenqian.bms.v2.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源映射路径
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    private CurrentUserArgumentResolver currentUserArgumentResolver;
    @Autowired
    private PageQueryArgumentResolver pageQueryArgumentResolver;
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private AccessLogInterceptor accessLogInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserArgumentResolver);
        resolvers.add(pageQueryArgumentResolver);
    }

    /**
     * @param [registry]
     * @return void
     * @description
     * @author LiBin
     * @date 2020/3/30 13:56
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/swagger-ui.html");
        excludePathPatterns.add("/swagger-resources/**");
        excludePathPatterns.add("/error");
        excludePathPatterns.add("/webjars/**");
        excludePathPatterns.add("/v2/login");
        excludePathPatterns.add("/v2/base/**");
        excludePathPatterns.add("/v2/common/**");
        excludePathPatterns.add("/v2/demo/**");
        // addPathPatterns 用于添加拦截规则 ， 先把所有路径都加入拦截， 再一个个排除
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/v2/**")
                .excludePathPatterns(excludePathPatterns);
        registry.addInterceptor(accessLogInterceptor)
                .addPathPatterns(new String[] { "/**" });
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}