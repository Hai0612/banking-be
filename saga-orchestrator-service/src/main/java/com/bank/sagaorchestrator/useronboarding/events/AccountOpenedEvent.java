package com.bank.common.saga.useronboarding.events;

import java.time.Instant;
import java.util.UUID;

import com.bank.common.entity.Account;
import com.bank.common.entity.User;
import com.bank.common.saga.event.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Event indicating an account was successfully opened.
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {

    Account account;

    User user;

    public static AccountOpenedEvent create(Long sagaId, Account account, User user) {
        return AccountOpenedEvent.builder()
            .eventId(UUID.randomUUID().toString())
            .sagaId(sagaId)
            .timestamp(Instant.now())
            .success(true)
            .errorMessage(null)
            .account(account)
            .user(user)
            .build();
    }
}
