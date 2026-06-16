package com.example.banking.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(String topic, String key, Object event) {
        log.info("Publishing event topic={} key={}", topic, key);
        kafkaTemplate.send(topic, key, event);
    }
}