package com.bank.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
//@Slf4j
//public class NotificationConsumer {
//
//    @KafkaListener(topics = "transfer-completed", groupId = "banking-group")
//    public void handleTransferCompleted(
//            ConsumerRecord<String, TransferCompletedEvent> record) {
//
//        // Tại đây traceId đã có trong MDC (OTel extract từ Kafka header)
//        // Log này sẽ có cùng traceId với log ở PaymentService producer
//        log.info("Received transfer-completed event key={} partition={} offset={}",
//                record.key(), record.partition(), record.offset());
//
//        TransferCompletedEvent event = record.value();
//        // Thêm business context
//        MDC.put("transactionId", event.getTransactionId());
//
//        try {
//            processNotification(event);
//            log.info("Notification sent transactionId={}", event.getTransactionId());
//        } finally {
//            MDC.remove("transactionId");
//        }
//    }
//}
