package com.bank.payment.dto;

import java.math.BigDecimal;

public record CreatePaymentRequest(

        String idempotencyKey,

        Long fromAccountId,

        Long toAccountId,

        BigDecimal amount,

        String currency
) {
}