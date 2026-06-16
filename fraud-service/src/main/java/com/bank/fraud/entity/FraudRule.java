package com.bank.reconciliation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "fraud_rules")
public class FraudRule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "jsonb")
    private String condition; // dynamic JSON
    private String action; // FLAG / BLOCK
    private LocalDateTime createdAt = LocalDateTime.now();

    public FraudRule() {}
    public FraudRule(String name, String condition, String action){
        this.name = name; this.condition = condition; this.action = action;
    }
    // getters
}
