package com.bank.sagaorchestrator.payment.events;

import com.bank.common.entity.Payment;
import com.bank.sagaorchestrator.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Event indicating payment has been successfully updated.
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentStatusUpdatedEvent extends BaseEvent {

    private Payment payment;

    public static PaymentStatusUpdatedEvent create(Long sagaId, Payment payment) {
        return PaymentStatusUpdatedEvent.builder()
            .eventId(java.util.UUID.randomUUID().toString())
            .sagaId(sagaId)
            .timestamp(java.time.Instant.now())
            .payment(payment)
            .build();
    }
}
