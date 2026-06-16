//package com.bank.auth.config;
//
//import org.slf4j.MDC;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.AsyncConfigurer;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.Map;
//import java.util.concurrent.Executor;
//import io.opentelemetry.context.Context;
//import io.opentelemetry.context.Scope;
//@Configuration
//@EnableAsync
//public class AsyncConfig implements AsyncConfigurer {
//
//    @Override
//    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10);
//        executor.setMaxPoolSize(50);
//        executor.setQueueCapacity(100);
//        executor.setThreadNamePrefix("banking-async-");
//
//        // Wrap để OTel context được copy sang thread mới
//        // Đây là key config — thiếu cái này trace sẽ bị đứt
//        executor.setTaskDecorator(runnable -> {
//            // Capture context của thread hiện tại (request thread)
//            Context currentContext = Context.current();
//            // Copy MDC sang thread mới
//            Map<String, String> mdcContext = MDC.getCopyOfContextMap();
//
//            return () -> {
//                try (Scope ignored = currentContext.makeCurrent()) {
//                    if (mdcContext != null) {
//                        MDC.setContextMap(mdcContext);
//                    }
//                    runnable.run();
//                } finally {
//                    MDC.clear();
//                }
//            };
//        });
//
//        executor.initialize();
//        return executor;
//    }
//}
