package com.bank.reconciliation.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_flags")
public class FraudFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long paymentId;
    private Long ruleId;
    private LocalDateTime flaggedAt = LocalDateTime.now();
    private Boolean resolved = false;

    public FraudFlag() {}
    public FraudFlag(Long paymentId, Long ruleId){
        this.paymentId = paymentId; this.ruleId = ruleId;
    }
    // getters/setters
}