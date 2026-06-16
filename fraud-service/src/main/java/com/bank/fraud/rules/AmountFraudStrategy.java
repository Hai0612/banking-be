package com.bank.reconciliation.rules;

import com.bank.reconciliation.dto.PaymentContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.math.BigDecimal;

public class AmountFraudStrategy implements FraudStrategy {

    @Override
    public boolean evaluate(JsonNode condition, PaymentContext context) {
        if (!condition.has("amount")) return false;
        String cond = condition.get("amount").asText(); // ">100000000"
        char op = cond.charAt(0);
        BigDecimal threshold = new BigDecimal(cond.substring(1));
        return switch(op){
            case '>' -> context.amount().compareTo(threshold) > 0;
            case '<' -> context.amount().compareTo(threshold) < 0;
            default -> false;
        };
    }
}