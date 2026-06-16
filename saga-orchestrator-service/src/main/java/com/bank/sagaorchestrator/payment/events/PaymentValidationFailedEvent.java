package com.bank.sagaorchestrator.payment.events;

import com.bank.common.entity.Payment;
import com.bank.sagaorchestrator.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Event indicating a payment validation has failed.
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentValidationFailedEvent extends BaseEvent {

    Payment payment;
    private String reason;

    public static PaymentValidationFailedEvent create(Long sagaId, Payment payment, String reason) {
        return PaymentValidationFailedEvent.builder()
            .eventId(java.util.UUID.randomUUID().toString())
            .sagaId(sagaId)
            .timestamp(java.time.Instant.now())
            .payment(payment)
            .reason(reason)
            .build();
    }
}
