package com.bank.reconciliation.rules;


import com.bank.reconciliation.dto.PaymentContext;
import com.fasterxml.jackson.databind.JsonNode;

// Strategy interface
public interface FraudStrategy {
    boolean evaluate(JsonNode condition, PaymentContext context);
}
