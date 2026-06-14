package com.core.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "core.security")
@Getter
@Setter
public class SecurityProperties {

    private String jwtSecret;

    private String internalApiKey;

    private List<String> publicUrls = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/actuator/health"
    );
}
