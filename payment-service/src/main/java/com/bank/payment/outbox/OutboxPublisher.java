//package com.bank.payment.outbox;
//
//import com.bank.payment.entity.PaymentOutbox;
//import com.bank.payment.repository.PaymentOutboxRepository;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OutboxPublisher {
//
//    private final PaymentOutboxRepository repo;
//
//    public OutboxPublisher(PaymentOutboxRepository repo){
//        this.repo = repo;
//    }
//
//    public void publish(PaymentOutbox outbox){
//        // here fake Kafka publish
//        System.out.printf("Publishing event %s: %s%n", outbox.getEventType(), outbox.getPayload());
//        outbox.setStatus("SENT");
//        outbox.setSentAt(java.time.LocalDateTime.now());
//        repo.save(outbox);
//    }
//}