package com.bank.payment.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentCompletedEvent(
        Long paymentId,
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount,
        String currency,
        UUID traceId
) {}