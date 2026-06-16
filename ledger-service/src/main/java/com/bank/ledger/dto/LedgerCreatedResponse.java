package src.main.java.com.bank.ledger.dto;


import java.util.List;
import java.util.UUID;

public record LedgerCreatedResponse(

        UUID transactionId,

        Integer totalEntries,

        List<LedgerEntryResponse> entries

) {
}
