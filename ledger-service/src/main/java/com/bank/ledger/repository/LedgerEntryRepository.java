package src.main.java.com.bank.ledger.repository;

import com.bank.ledger.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LedgerEntryRepository
        extends JpaRepository<LedgerEntry, Long> {

    List<LedgerEntry> findByTransactionId(UUID transactionId);

    boolean existsByTransactionId(UUID transactionId);
}