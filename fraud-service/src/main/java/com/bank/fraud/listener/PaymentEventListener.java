package com.bank.reconciliation.listener;


import com.bank.reconciliation.dto.PaymentCompletedEvent;
import com.bank.reconciliation.service.FraudService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventListener {

    private final FraudService fraudService;

    public PaymentEventListener(FraudService fraudService){
        this.fraudService = fraudService;
    }

    @KafkaListener(topics = "payment-completed", groupId = "fraud-service")
    public void handle(PaymentCompletedEvent event){
        try{
            fraudService.checkPayment(
                    event.paymentId(),
                    event.amount(),
                    event.fromAccountId(),
                    event.toAccountId()
            );
        }catch(Exception ex){
            // log + send alert
            System.err.println("Fraud check failed: " + ex.getMessage());
            // Could send event to "payment-failed" topic for saga compensation
        }
    }
}