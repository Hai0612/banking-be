package com.bank.sagaorchestrator.useronboarding.events;

import com.bank.sagaorchestrator.event.BaseEvent;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * Event indicating user creation failed.
 */
@Getter
@ToString
@SuperBuilder
public class UserCreationFailedEvent extends BaseEvent {

    public UserCreationFailedEvent() {
    }

    public UserCreationFailedEvent(String eventId, Long sagaId, Instant timestamp, boolean success, String errorMessage) {
        super(eventId, sagaId, timestamp, success, errorMessage);
    }

    public static UserCreationFailedEvent create(Long sagaId, String errorMessage) {
        return UserCreationFailedEvent.builder()
            .eventId(java.util.UUID.randomUUID().toString())
            .sagaId(sagaId)
            .timestamp(Instant.now())
            .success(false)
            .errorMessage(errorMessage)
            .build();
    }
}
