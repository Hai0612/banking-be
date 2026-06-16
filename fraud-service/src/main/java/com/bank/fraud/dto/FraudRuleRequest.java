package com.bank.reconciliation.dto;

public record FraudRuleRequest(String name, String condition, String action) {}
