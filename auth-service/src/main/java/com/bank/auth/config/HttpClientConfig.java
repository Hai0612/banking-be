package com.bank.auth.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class HttpClientConfig {

    // QUAN TRỌNG: phải inject ObservationRegistry để OTel hook vào được
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .connectTimeout(Duration.ofSeconds(5))
                .readTimeout(Duration.ofSeconds(10))
                .build();
    }

    // Nếu dùng RestClient (Spring Boot 3.2+)
    @Bean
    public RestClient restClient(RestClient.Builder builder,
                                 ObservationRegistry observationRegistry) {
        return builder
                .observationRegistry(observationRegistry)
                .build();
    }
}

