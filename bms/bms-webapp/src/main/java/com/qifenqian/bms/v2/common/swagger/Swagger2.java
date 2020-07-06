package com.qifenqian.bms.v2.common.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：Swagger2
 * Description：TODO
 * Author: LiBin
 * Date：2020/1/15 10:51 上午
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable}") //开启访问接口文档的权限
public class Swagger2 {
    @Bean
    public Docket userRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("BMS后台管理系统")  //模块名称
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qifenqian.bms.v2.biz"))  //扫描的控制器路径
                .paths(PathSelectors.any())
                .build().securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("BMS后台管理系统开发接口文档")    //接口文档标题
                .description("此文档仅供开发技术组领导、开发人员使用")   //描述
                .version("1.0")  //版本号
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> list = new ArrayList<>();
        ApiKey apiKey = new ApiKey("token", "token", "header");
        ApiKey apiKey1 = new ApiKey("loginId", "loginId", "header");
        list.add(apiKey);
        list.add(apiKey1);
        return list;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> list = new ArrayList<>();
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
        list.add(securityContext);
        return list;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope authorizationScope1 = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        AuthorizationScope[] authorizationScopes1 = new AuthorizationScope[1];
        authorizationScopes1[0] = authorizationScope;
        SecurityReference securityReference = new SecurityReference("token", authorizationScopes);
        List<SecurityReference> list = new ArrayList<>();
        SecurityReference securityReference1 = new SecurityReference("loginId", authorizationScopes1);
        list.add(securityReference);
        list.add(securityReference1);
        return list;
    }
}
