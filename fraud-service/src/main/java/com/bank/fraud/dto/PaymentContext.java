package com.bank.reconciliation.dto;

import java.math.BigDecimal;

public record PaymentContext(BigDecimal amount, Long fromAccountId, Long toAccountId) {}

