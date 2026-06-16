package com.bank.customer.dto.request;


import com.bank.account.enums.AccountType;

import java.math.BigDecimal;

public record AccountResponse(
        Long id,
        Long customerId,
        AccountType accountType,
        String currency,
        BigDecimal balance,
        String status
) {}