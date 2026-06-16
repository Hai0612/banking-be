package com.bank.payment.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount,
        String currency,
        String idempotencyKey
) {}
