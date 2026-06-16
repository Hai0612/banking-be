package com.bank.customer.entity;


import com.bank.account.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String currency = "USD";

    private BigDecimal currentBalance = BigDecimal.ZERO;

    private String status = "ACTIVE";

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