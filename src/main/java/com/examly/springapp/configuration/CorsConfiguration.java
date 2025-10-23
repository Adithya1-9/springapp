package com.examly.springapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        // allow local dev and examly preview domains; use allowedOriginPatterns for flexible matching
        .allowedOriginPatterns("http://localhost:*", "https://*.premiumproject.examly.io", "http://localhost:3000", "http://localhost:8080")
        .allowCredentials(true)
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
        .allowedHeaders("*")
        .maxAge(3600);

    }
}
// https://8081-ddaafeadaccddcacbeacdbbfecbffbbdeeecceea.premiumproject.examly.io/

