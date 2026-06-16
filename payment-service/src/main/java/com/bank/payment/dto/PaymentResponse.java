package com.bank.payment.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


public record PaymentResponse(

        Long id,

        UUID transactionId,

        Long fromAccountId,

        Long toAccountId,

        BigDecimal amount,

        String currency,

        String status,

        Instant createdAt
) {
}