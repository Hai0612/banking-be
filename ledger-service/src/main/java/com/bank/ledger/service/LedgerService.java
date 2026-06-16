package src.main.java.com.bank.ledger.service;

import com.bank.ledger.entity.LedgerEntry;
import com.bank.ledger.enums.EntryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.main.java.com.bank.ledger.dto.CreateLedgerRequest;
import src.main.java.com.bank.ledger.dto.LedgerCreatedResponse;
import src.main.java.com.bank.ledger.dto.LedgerEntryResponse;
import src.main.java.com.bank.ledger.enums.LedgerDirection;
import src.main.java.com.bank.ledger.repository.LedgerEntryRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LedgerService {

    private final LedgerEntryRepository repository;
    private final OutboxService outboxService;

    @Transactional
    public LedgerCreatedResponse createEntries(
            CreateLedgerRequest request
    ) {

        if (repository.existsByTransactionId(
                request.transactionId()
        )) {

            throw new RuntimeException(
                    "Transaction already exists"
            );
        }

        LedgerEntry debit =
                LedgerEntry.builder()
                        .transactionId(request.transactionId())
                        .accountId(request.debitAccountId())
                        .direction(LedgerDirection.DEBIT)
                        .amount(request.amount())
                        .currency(request.currency())
                        .description(request.description())
                        .build();

        LedgerEntry credit =
                LedgerEntry.builder()
                        .transactionId(request.transactionId())
                        .accountId(request.creditAccountId())
                        .direction(LedgerDirection.CREDIT)
                        .amount(request.amount())
                        .currency(request.currency())
                        .description(request.description())
                        .build();

        repository.save(debit);
        repository.save(credit);

        List<LedgerEntryResponse> entries =
                List.of(
                        LedgerMapper.toResponse(debit),
                        LedgerMapper.toResponse(credit)
                );

        return new LedgerCreatedResponse(
                request.transactionId(),
                entries.size(),
                entries
        );
    }
}