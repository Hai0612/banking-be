package com.bank.payment.entity;

import com.bank.payment.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(
                        name = "idx_payment_tx",
                        columnList = "transaction_id"
                ),
                @Index(
                        name = "idx_payment_status",
                        columnList = "status"
                )
        }
)
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID transactionId;

    private Long fromAccountId;

    private Long toAccountId;

    private BigDecimal amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Version
    private Long version;

    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    void prePersist() {

        createdAt = Instant.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    void preUpdate() {

        updatedAt = Instant.now();
    }
}