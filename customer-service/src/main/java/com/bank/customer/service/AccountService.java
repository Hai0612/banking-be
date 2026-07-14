package com.bank.customer.service;

import com.bank.customer.dto.request.CreateAccountRequest;
import com.bank.customer.dto.response.AccountResponse;
import com.bank.customer.entity.Account;
import com.bank.customer.entity.AccountSnapshot;
import com.bank.customer.repository.AccountRepository;
import com.bank.customer.repository.AccountSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountSnapshotRepository snapshotRepository;

    public AccountResponse create(CreateAccountRequest req) {

        Account acc = Account.builder()
                .accountNo(UUID.randomUUID().toString().substring(0, 10).toUpperCase())
                .accountType(req.accountType())
                .customerId(req.customerId())
                .currency(req.currency() != null ? req.currency() : "USD")
                .availableBalance(BigDecimal.ZERO)
                .holdBalance(BigDecimal.ZERO)
                .status("ACTIVE")
                .build();

        Account saved = accountRepository.save(acc);

        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public AccountResponse get(Long id) {
        return accountRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> getByCustomer(Long customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AccountResponse updateBalance(Long accountId, BigDecimal delta) {

        Account acc = accountRepository.findById(accountId)
                .orElseThrow();

        acc.setAvailableBalance(acc.getAvailableBalance().add(delta));

        Account saved = accountRepository.save(acc);

        snapshot(saved);

        return toResponse(saved);
    }

    private void snapshot(Account acc) {

        AccountSnapshot snap = AccountSnapshot.builder()
                .accountId(acc.getId())
                .balance(acc.getAvailableBalance())
                .build();

        snapshotRepository.save(snap);
    }

    private AccountResponse toResponse(Account acc) {
        return new AccountResponse(
                acc.getId(),
                acc.getCustomerId(),
                acc.getAccountType(),
                acc.getCurrency(),
                acc.getAvailableBalance(),
                acc.getStatus()
        );
    }
}
