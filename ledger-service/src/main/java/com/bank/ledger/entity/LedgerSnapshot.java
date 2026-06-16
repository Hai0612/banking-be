package com.bank.ledger.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
@Entity
@Table(name = "ledger_snapshots")
@Getter
@Setter
public class LedgerSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private BigDecimal balance;

    private Instant snapshotTime;
}