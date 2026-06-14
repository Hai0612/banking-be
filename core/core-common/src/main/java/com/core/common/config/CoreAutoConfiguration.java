// src/main/java/com/viettel/app/core/config/CoreAutoConfiguration.java

package com.core.config;

import com.core.common.ResponseFactory;
import io.micrometer.tracing.Tracer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ConditionalOnWebApplication
@ComponentScan
@EntityScan
// 1. Explicitly import the Redis Config (The Umbrella Pattern)
public class CoreAutoConfiguration {

    @Bean
    public ResponseFactory responseFactory(Tracer tracer, MessageSource messageSource) {
        return new ResponseFactory(tracer, messageSource);
    }

}