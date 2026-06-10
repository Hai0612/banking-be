package com.bank.fraud.dto;

import java.math.BigDecimal;

public record AccountBalanceCheck(
        Long accountId,
        BigDecimal accountBalance,
        BigDecimal ledgerBalance
) {}
