package com.bank.fraud.service;

import com.bank.fraud.dto.CreateAccountRequest;
import com.bank.fraud.entity.Account;
import com.bank.fraud.entity.AccountSnapshot;
import com.bank.fraud.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountSnapshotRepository snapshotRepository;

    public AccountService(AccountRepository accountRepository,
                          AccountSnapshotRepository snapshotRepository) {
        this.accountRepository = accountRepository;
        this.snapshotRepository = snapshotRepository;
    }

    public Account create(CreateAccountRequest req) {

        Account acc = new Account();

        acc.setCustomerId(req.customerId());
        acc.setAccountType(req.accountType());
        acc.setCurrency(req.currency());

        acc.setCurrentBalance(
                req.initialBalance() == null
                        ? BigDecimal.ZERO
                        : req.initialBalance()
        );

        acc.setStatus("ACTIVE");

        return accountRepository.save(acc);
    }

    @Transactional
    public Account debit(Long accountId, BigDecimal amount) {

        Account acc = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!acc.canDebit(amount)) {
            throw new RuntimeException("Insufficient balance");
        }

        acc.debit(amount);

        snapshotRepository.save(
                new AccountSnapshot(acc.getId(), acc.getCurrentBalance())
        );

        return acc;
    }

    @Transactional
    public Account credit(Long accountId, BigDecimal amount) {

        Account acc = accountRepository.findById(accountId)
                .orElseThrow();

        acc.credit(amount);

        snapshotRepository.save(
                new AccountSnapshot(acc.getId(), acc.getCurrentBalance())
        );

        return acc;
    }
}