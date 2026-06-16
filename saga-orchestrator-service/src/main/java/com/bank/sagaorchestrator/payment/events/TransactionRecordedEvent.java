package com.bank.sagaorchestrator.payment.events;

import com.bank.common.entity.Payment;
import com.bank.sagaorchestrator.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Event indicating transaction has been successfully recorded.
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TransactionRecordedEvent extends BaseEvent {

    Payment payment;

    public static TransactionRecordedEvent create(Long sagaId, Payment payment) {
        return TransactionRecordedEvent.builder()
            .eventId(java.util.UUID.randomUUID().toString())
            .sagaId(sagaId)
            .timestamp(java.time.Instant.now())
            .payment(payment)
            .build();
    }
}
