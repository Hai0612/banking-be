package com.core.security.config;

import com.core.security.auth.JwtAuthenticationConverter;
import com.core.security.exception.CustomAccessDeniedHandler;
import com.core.security.exception.CustomAuthenticationEntryPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.core.security.config.ResponseFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
@EntityScan
public class CoreSecurityConfig {



    // 2. Provides your custom converter (that extracts preferred_username, azp, jti)
    @Bean
    public JwtAuthenticationConverter customJwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint(
            ObjectMapper objectMapper,
            ResponseFactory responseFactory) {
        return new CustomAuthenticationEntryPoint(objectMapper, responseFactory);
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(
            ObjectMapper objectMapper,
            ResponseFactory responseFactory) {
        return new CustomAccessDeniedHandler(objectMapper, responseFactory);
    }
}