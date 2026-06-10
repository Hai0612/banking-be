package com.bank.fraud.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reconciliation_discrepancies")
public class ReconciliationDiscrepancy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reconciliationRunId;

    private Long accountId;

    private BigDecimal ledgerBalance;

    private BigDecimal accountBalance;

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters, setters
}