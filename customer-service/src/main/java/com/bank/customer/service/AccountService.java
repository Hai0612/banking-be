package com.bank.customer.service;

import com.bank.account.dto.AccountResponse;
import com.bank.account.dto.CreateAccountRequest;
import com.bank.account.entity.Account;
import com.bank.account.entity.AccountSnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountSnapshotRepository snapshotRepository;

    public AccountResponse create(CreateAccountRequest req) {

        Account acc = new Account();
        acc.setCustomerId(req.customerId());
        acc.setAccountType(req.accountType());
        acc.setCurrency(req.currency() != null ? req.currency() : "USD");
        acc.setCurrentBalance(BigDecimal.ZERO);
        acc.setStatus("ACTIVE");

        Account saved = accountRepository.save(acc);

        return toResponse(saved);
    }

    public AccountResponse get(Long id) {
        return accountRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow();
    }

    public List<AccountResponse> getByCustomer(Long customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AccountResponse updateBalance(Long accountId, BigDecimal delta) {

        Account acc = accountRepository.findById(accountId)
                .orElseThrow();

        acc.setCurrentBalance(acc.getCurrentBalance().add(delta));

        Account saved = accountRepository.save(acc);

        snapshot(saved);

        return toResponse(saved);
    }

    private void snapshot(Account acc) {

        AccountSnapshot snap = new AccountSnapshot();
        snap.setAccountId(acc.getId());
        snap.setBalance(acc.getCurrentBalance());

        snapshotRepository.save(snap);
    }

    private AccountResponse toResponse(Account acc) {
        return new AccountResponse(
                acc.getId(),
                acc.getCustomerId(),
                acc.getAccountType(),
                acc.getCurrency(),
                acc.getCurrentBalance(),
                acc.getStatus()
        );
    }
}