package com.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisCacheService {

    private final StringRedisTemplate redisTemplate;

    public void set(String key, String value, long ttlSeconds) {
        redisTemplate.opsForValue()
                .set(key, value, Duration.ofSeconds(ttlSeconds));
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}