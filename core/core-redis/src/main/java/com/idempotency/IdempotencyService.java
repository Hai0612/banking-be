package com.idempotency;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private final StringRedisTemplate redisTemplate;

    public boolean isProcessed(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void markProcessed(String key, String value) {
        redisTemplate.opsForValue()
                .set(key, value, Duration.ofHours(24));
    }
}