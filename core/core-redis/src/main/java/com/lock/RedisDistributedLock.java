package com.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisDistributedLock {

    private final RedissonClient redissonClient;

    public <T> T executeWithLock(String key, long waitTime, long leaseTime,
                                 TimeUnit unit, LockCallback<T> callback) {

        RLock lock = redissonClient.getLock(key);

        try {
            boolean acquired = lock.tryLock(waitTime, leaseTime, unit);

            if (!acquired) {
                throw new RuntimeException("Could not acquire lock: " + key);
            }

            return callback.doWithLock();

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public interface LockCallback<T> {
        T doWithLock();
    }
}