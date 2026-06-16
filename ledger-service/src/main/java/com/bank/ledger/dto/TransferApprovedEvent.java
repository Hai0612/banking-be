package src.main.java.com.bank.ledger.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferApprovedEvent(

        UUID transactionId,

        Long fromAccountId,

        Long toAccountId,

        BigDecimal amount,

        String currency,

        String traceId

) {
}