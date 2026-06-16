package src.main.java.com.bank.ledger.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateLedgerRequest(

        @NotNull
        UUID transactionId,

        @NotNull
        Long debitAccountId,

        @NotNull
        Long creditAccountId,

        @NotNull
        @DecimalMin("0.01")
        BigDecimal amount,

        @NotBlank
        String currency,

        String description

) {
}
