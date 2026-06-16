package src.main.java.com.bank.ledger.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import src.main.java.com.bank.ledger.dto.CreateLedgerEntryCommand;
import src.main.java.com.bank.ledger.dto.TransferApprovedEvent;
import src.main.java.com.bank.ledger.service.LedgerService;

@Component
@RequiredArgsConstructor
@Slf4j
public class LedgerKafkaConsumer {

    private final LedgerService ledgerService;

    @KafkaListener(
            topics = "transfer-approved",
            groupId = "ledger-service"
    )
    public void consume(
            TransferApprovedEvent event
    ) {

        ledgerService.createDoubleEntry(
                new CreateLedgerEntryCommand(
                        event.transactionId(),
                        event.fromAccountId(),
                        event.toAccountId(),
                        event.amount(),
                        event.currency(),
                        "Transfer"
                )
        );
    }
}
