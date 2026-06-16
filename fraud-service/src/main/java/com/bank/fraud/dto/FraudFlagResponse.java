package com.bank.reconciliation.dto;

public record FraudFlagResponse(Long id, Long paymentId, Long ruleId, Boolean resolved) {}
