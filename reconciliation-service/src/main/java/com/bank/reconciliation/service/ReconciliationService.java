package com.bank.fraud.service;

import com.bank.fraud.dto.AccountBalanceCheck;
import com.bank.fraud.entity.ReconciliationRun;
import com.bank.fraud.entity.ReconciliationDiscrepancy;
import com.bank.fraud.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReconciliationService {

    private final ReconciliationRunRepository runRepo;
    private final ReconciliationDiscrepancyRepository discrepancyRepo;
    private final AccountServiceClient accountClient; // gọi Account Service
    private final LedgerServiceClient ledgerClient;   // gọi Ledger Service

    public ReconciliationService(ReconciliationRunRepository runRepo,
                                 ReconciliationDiscrepancyRepository discrepancyRepo,
                                 AccountServiceClient accountClient,
                                 LedgerServiceClient ledgerClient) {
        this.runRepo = runRepo;
        this.discrepancyRepo = discrepancyRepo;
        this.accountClient = accountClient;
        this.ledgerClient = ledgerClient;
    }

    @Transactional
    public ReconciliationRun runReconciliation(LocalDate runDate) {

        ReconciliationRun run = new ReconciliationRun();
        run.setRunDate(runDate);
        run.setStatus("PENDING");
        run.setCreatedAt(LocalDateTime.now());
        run = runRepo.save(run);

        int discrepancyCount = 0;

        List<AccountBalanceCheck> balances = accountClient.getAllAccountBalances();

        for (AccountBalanceCheck check : balances) {
            BigDecimal ledgerBalance = ledgerClient.getLedgerBalance(check.accountId());

            if (ledgerBalance.compareTo(check.accountBalance()) != 0) {
                discrepancyCount++;

                ReconciliationDiscrepancy d = new ReconciliationDiscrepancy();
                d.setReconciliationRunId(run.getId());
                d.setAccountId(check.accountId());
                d.setLedgerBalance(ledgerBalance);
                d.setAccountBalance(check.accountBalance());
                d.setCreatedAt(LocalDateTime.now());
                discrepancyRepo.save(d);

                // Optional: alerting / publish Kafka / email
                System.out.println("Discrepancy detected for account: " + check.accountId());
            }
        }

        run.setDiscrepancyCount(discrepancyCount);
        run.setStatus("SUCCESS");
        run.setCompletedAt(LocalDateTime.now());
        runRepo.save(run);

        return run;
    }
}