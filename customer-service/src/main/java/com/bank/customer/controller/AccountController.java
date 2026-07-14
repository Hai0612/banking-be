package com.bank.customer.controller;

import com.bank.customer.dto.request.CreateAccountRequest;
import com.bank.customer.dto.response.AccountResponse;
import com.bank.customer.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountResponse create(@RequestBody CreateAccountRequest req) {
        return accountService.create(req);
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable Long id) {
        return accountService.get(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountResponse> getByCustomer(@PathVariable Long customerId) {
        return accountService.getByCustomer(customerId);
    }

    @PatchMapping("/{id}/balance")
    public AccountResponse updateBalance(
            @PathVariable Long id,
            @RequestParam BigDecimal delta
    ) {
        return accountService.updateBalance(id, delta);
    }
}