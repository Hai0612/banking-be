package com.bank.sagaorchestrator.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "saga_instances")
public class SagaInstance {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String sagaId;

    private String transactionId;

    private String state; // STARTED, COMPLETED, FAILED, COMPENSATED

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public SagaInstance() {}
}