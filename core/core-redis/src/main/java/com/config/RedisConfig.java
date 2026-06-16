package com.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@AutoConfiguration
@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(2);

        return Redisson.create(config);
    }
}