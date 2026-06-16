package com.bank.sagaorchestrator.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "saga_steps")
public class SagaStep {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sagaId;

    private String stepName;

    private String status; // PENDING, SUCCESS, FAILED, COMPENSATED

    private Integer retryCount = 0;

    @Column(columnDefinition = "jsonb")
    private String payload;

    private LocalDateTime executedAt = LocalDateTime.now();

    public SagaStep() {}
}