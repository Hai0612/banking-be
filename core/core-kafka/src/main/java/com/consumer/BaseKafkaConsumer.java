package com.consumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseKafkaConsumer {

    protected void logReceived(String topic, Object payload) {
        log.info("Consumed topic={} payload={}", topic, payload);
    }
}