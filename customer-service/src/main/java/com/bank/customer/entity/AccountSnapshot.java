package com.bank.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "account_snapshots")
@Getter
@Setter
public class AccountSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private BigDecimal balance;

    private Instant snapshotTime = Instant.now();
}