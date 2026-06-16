package com.bank.sagaorchestrator.dto;

import java.math.BigDecimal;
import java.util.UUID;



public record SagaCommand(
        String sagaId,
        String stepName,
        String payload
) {}