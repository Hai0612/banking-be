package com.bank.fraud.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reconciliation_runs")
@Data
public class ReconciliationRun {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate runDate;

    private String status = "PENDING"; // PENDING, SUCCESS, FAILED

    private Integer discrepancyCount = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime completedAt;

    // getters, setters
}