package com.bank.customer.dto.request;


import java.math.BigDecimal;

public record TransferRequest(
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount
) {}
