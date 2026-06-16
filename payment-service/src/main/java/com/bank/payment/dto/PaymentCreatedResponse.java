package com.bank.payment.dto;

import java.time.Instant;
import java.util.UUID;

public record PaymentCreatedResponse(

        UUID transactionId,

        Long paymentId,

        String status,

        Instant createdAt
) {
}