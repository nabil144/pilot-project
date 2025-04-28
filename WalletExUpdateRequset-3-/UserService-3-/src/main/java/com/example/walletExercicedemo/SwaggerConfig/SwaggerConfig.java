package com.example.walletExercicedemo.SwaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("User-api")  // Name of your group
                .packagesToScan("com.example.walletExercicedemo.controller")  // Replace with your package name
                .build();
    }
    @Bean
    public OpenAPI walletServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Service API")
                        .description("API documentation for transaction services.")
                        .version("1.0.0")
                        .termsOfService("http://walletapp.com/terms")
                        .contact(new Contact()
                                .name("API Support")
                                .url("http://walletapp.com/support")
                                .email("support@walletapp.com")
                        )
                );
    }
    }

