package com.example.TransactionServicedemo.SwaggerConfig;

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
                .group("transaction-api")
                .packagesToScan("com.example.TransactionServicedemo.controller")  // Adjust this package name to your actual one
                .build();
    }

    @Bean
    public OpenAPI transactionServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transaction Service API")
                        .description("API documentation for transaction services.")
                        .version("1.0.0")
                        .termsOfService("http://transactionapp.com/terms")
                        .contact(new Contact()
                                .name("API Support")
                                .url("http://transactionapp.com/support")
                                .email("support@transactionapp.com")
                        )
                );
    }

}
