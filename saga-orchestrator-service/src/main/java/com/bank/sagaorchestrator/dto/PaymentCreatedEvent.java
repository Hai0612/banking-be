package com.bank.sagaorchestrator.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentCreatedEvent(
        String sagaId,
        Long paymentId,
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount,
        String currency,
        UUID traceId
) {}
