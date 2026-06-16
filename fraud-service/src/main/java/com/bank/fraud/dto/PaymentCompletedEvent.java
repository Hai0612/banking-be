package com.bank.reconciliation.dto;


import java.util.UUID;


public record PaymentCompletedEvent(
        Long paymentId,
        Long fromAccountId,
        Long toAccountId,
        String currency,
        java.math.BigDecimal amount,
        UUID traceId
) {}