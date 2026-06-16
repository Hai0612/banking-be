package com.bank.payment.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "payment_idempotency_keys")
@Getter
@Setter
public class PaymentIdempotencyKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idempotencyKey;

    private Long paymentId;

    private String status;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> response;

    private Instant createdAt;

    @PrePersist
    void prePersist() {

        createdAt = Instant.now();
    }
}