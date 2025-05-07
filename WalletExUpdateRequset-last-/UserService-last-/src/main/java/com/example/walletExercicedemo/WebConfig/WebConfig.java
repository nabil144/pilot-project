package com.example.walletExercicedemo.WebConfig;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow requests from both UserService and TransactionService origins (Swagger UIs)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:8081")  // Allow cross-origin requests from these origins
                .allowedMethods("GET", "POST", "PUT", "DELETE");  // Allow all necessary HTTP methods
    }
}
