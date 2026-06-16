package src.main.java.com.bank.ledger.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateLedgerEntryCommand(

        UUID transactionId,

        Long debitAccountId,

        Long creditAccountId,

        BigDecimal amount,

        String currency,

        String description

) {
}
