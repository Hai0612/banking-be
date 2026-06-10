package com.bank.fraud.controller;

import com.bank.fraud.dto.AccountResponse;
import com.bank.fraud.dto.CreateAccountRequest;
import com.bank.fraud.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse create(@RequestBody CreateAccountRequest req) {

        var acc = accountService.create(req);

        return new AccountResponse(
                acc.getId(),
                acc.getCustomerId(),
                acc.getAccountType(),
                acc.getCurrency(),
                acc.getCurrentBalance(),
                acc.getStatus(),
                acc.getVersion()
        );
    }
}