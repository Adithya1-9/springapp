package com.examly.springapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                // Replace with your base package where controllers exist
                .apis(RequestHandlerSelectors.basePackage("package com.examly.springapp.controller;"))
                .paths(PathSelectors.any())
                .build();
    }
}
