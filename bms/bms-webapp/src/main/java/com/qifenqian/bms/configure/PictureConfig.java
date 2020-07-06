package com.qifenqian.bms.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PictureConfig implements WebMvcConfigurer{

	@Value("${images.relativePaths}")
    private String relativePaths;
    @Value("${images.absolutePaths}")
    private String absolutePaths;
    /**
     * 资源映射路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:/data/nfsshare/upload/picture/");
    }
	
	

}
