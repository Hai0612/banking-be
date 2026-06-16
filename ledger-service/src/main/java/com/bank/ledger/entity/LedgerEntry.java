package com.bank.ledger.entity;

import com.bank.ledger.enums.EntryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import src.main.java.com.bank.ledger.enums.LedgerDirection;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Entity
@Table(
        name = "ledger_entries",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_ledger_tx_account_direction",
                        columnNames = {
                                "transaction_id",
                                "account_id",
                                "direction"
                        }
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID transactionId;

    private Long accountId;

    @Enumerated(EnumType.STRING)
    private LedgerDirection direction;

    private BigDecimal amount;

    private String currency;

    private String description;

    private Instant createdAt;

    @Builder
    public LedgerEntry(
            UUID transactionId,
            Long accountId,
            LedgerDirection direction,
            BigDecimal amount,
            String currency,
            String description
    ) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.direction = direction;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.createdAt = Instant.now();
    }
}