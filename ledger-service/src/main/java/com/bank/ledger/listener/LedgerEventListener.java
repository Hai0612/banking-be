//package com.bank.ledger.listener;
//
//import com.bank.ledger.dto.LedgerEvent;
//import com.bank.ledger.service.LedgerService;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LedgerEventListener {
//
//    private final LedgerService ledgerService;
//
//    public LedgerEventListener(LedgerService ledgerService) {
//        this.ledgerService = ledgerService;
//    }
//
//    @KafkaListener(topics = "payment-completed", groupId = "ledger-service")
//    public void handle(LedgerEvent event) {
//
//        ledgerService.recordTransaction(
//                event.paymentId(),
//                event.fromAccountId(),
//                event.toAccountId(),
//                event.amount(),
//                event.currency()
//        );
//    }
//}