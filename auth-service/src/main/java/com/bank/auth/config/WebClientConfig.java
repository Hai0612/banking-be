package com.bank.auth.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder,
                               ObservationRegistry observationRegistry) {
        return builder
                // OTel tự inject traceparent header
                .observationRegistry(observationRegistry)
                .build();
    }
}
