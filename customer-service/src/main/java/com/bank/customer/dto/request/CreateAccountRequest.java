package com.bank.customer.dto.request;


import com.bank.account.enums.AccountType;

public record CreateAccountRequest(
        Long customerId,
        AccountType accountType,
        String currency
) {}