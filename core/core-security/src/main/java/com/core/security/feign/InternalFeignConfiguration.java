package com.core.security.feign;

import com.core.security.constants.SecurityConstants;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InternalFeignConfiguration {

    @Value("${core.security.internal-api-key}")
    private String internalApiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {

        return template ->
                template.header(
                        SecurityConstants.INTERNAL_API_KEY_HEADER,
                        internalApiKey
                );
    }
}
