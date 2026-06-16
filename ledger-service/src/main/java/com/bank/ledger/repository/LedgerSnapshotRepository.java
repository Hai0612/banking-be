package com.bank.ledger.repository;

import com.bank.ledger.entity.LedgerSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LedgerSnapshotRepository
        extends JpaRepository<LedgerSnapshot, Long> {

    Optional<LedgerSnapshot> findTopByAccountIdOrderBySnapshotTimeDesc(
            Long accountId
    );
}