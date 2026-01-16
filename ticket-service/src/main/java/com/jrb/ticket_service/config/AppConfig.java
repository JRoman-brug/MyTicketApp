package com.jrb.ticket_service.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class AppConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // ¡OJO AQUÍ! Pon la URL exacta de tu frontend (Vite suele usar el puerto 5173)
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));

        // Métodos HTTP que vas a permitir
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Cabeceras permitidas (Authorization es vital si usas JWT)
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // Permitir credenciales (cookies o cabeceras de autenticación)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Aplica esta configuración a TODAS las rutas de tu API
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
