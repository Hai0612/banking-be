package com.bank.reconciliation.dto;

public record FraudRuleResponse(Long id, String name, String condition, String action) {}
