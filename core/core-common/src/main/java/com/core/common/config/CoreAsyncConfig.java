package com.core.config;

import io.micrometer.context.ContextSnapshotFactory; // New Import
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * Truyền SecurityContextHolder
 * MDC
 * LocaleContextHolder
 * Observation
 * Tracing khi gọi @Async
 */
@Configuration
@EnableAsync
public class CoreAsyncConfig {

    /**
     * 1. The Decorator is generic and applies to ALL services.
     * UPDATE: Uses ContextSnapshotFactory to avoid deprecation.
     */
    @Bean
    public TaskDecorator contextPropagatingTaskDecorator() {
        return runnable -> ContextSnapshotFactory.builder()
                .build()
                .captureAll()
                .wrap(runnable);
    }

    /**
     * 2. The Executor is "Conditional".
     * Using Java 21 Virtual Threads for high throughput.
     */
    @Bean(name = "taskExecutor")
    @ConditionalOnMissingBean(name = "taskExecutor")
    public Executor taskExecutor(TaskDecorator decorator) {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setVirtualThreads(true); // Java 21 Power Move
        executor.setTaskDecorator(decorator);
        return executor;
    }
}