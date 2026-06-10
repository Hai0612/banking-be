package com.bank.fraud.scheduler;

import com.bank.fraud.service.ReconciliationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReconciliationScheduler {

    private final ReconciliationService service;

    public ReconciliationScheduler(ReconciliationService service) {
        this.service = service;
    }

    // Chạy mỗi ngày lúc 01:00 AM
    @Scheduled(cron = "0 0 1 * * ?")
    public void runDailyReconciliation() {
        service.runReconciliation(LocalDate.now().minusDays(1));
    }
}