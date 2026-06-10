package com.bank.fraud.dto;


public record ReconciliationReport(
        Long runId,
        int discrepancyCount
) {}