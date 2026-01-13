package com.jrb.ticket_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.build();
    }

}
