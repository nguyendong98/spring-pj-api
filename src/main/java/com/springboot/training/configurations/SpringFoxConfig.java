package com.springboot.training.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket swaggerUserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Default")
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.springboot.training.controllers.api"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(List.of(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Training System - REST APIs")
                .description("Spring Boot  application.").termsOfServiceUrl("")
                .license("Apache License Version 2.0")
                .version("0.0.1")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
    }
}