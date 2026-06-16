package com.config;

import com.constants.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic transferCompletedTopic() {
        return new NewTopic(KafkaTopics.TRANSFER_COMPLETED, 3, (short) 1);
    }
}