package com.jrb.ticket_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * OpenAPI/Swagger configuration for the Ticket Service.
 * Configures API documentation metadata.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configures the OpenAPI documentation with custom information.
     * 
     * @return configured OpenAPI instance
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ticket Service API")
                        .version("1.0.0")
                        .description("API for managing movies, halls, showtimes, and ticket reservations"));
    }
}
