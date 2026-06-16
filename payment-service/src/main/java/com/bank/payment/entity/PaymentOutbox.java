package com.bank.payment.entity;


import com.bank.payment.enums.OutboxStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "payment_outbox")
@Getter
@Setter
public class PaymentOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID transactionId;

    private Long paymentId;

    private String eventType;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> payload;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    private Integer retryCount;

    private Instant createdAt;

    private Instant sentAt;

    @PrePersist
    void prePersist() {

        createdAt = Instant.now();

        if (retryCount == null) {
            retryCount = 0;
        }
    }
}