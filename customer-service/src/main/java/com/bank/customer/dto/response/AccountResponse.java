package com.bank.customer.dto.response;

import com.bank.customer.enums.AccountType;
import java.math.BigDecimal;

public record AccountResponse(
        Long id,
        Long customerId,
        AccountType accountType,
        String currency,
        BigDecimal balance,
        String status
) {}
