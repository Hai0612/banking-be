package src.main.java.com.bank.ledger.dto;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record LedgerEntryResponse(

        Long id,

        UUID transactionId,

        Long accountId,

        String direction,

        BigDecimal amount,

        String currency,

        String description,

        Instant createdAt

) {
}