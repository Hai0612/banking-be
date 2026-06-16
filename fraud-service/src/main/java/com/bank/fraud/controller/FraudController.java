package com.bank.reconciliation.controller;

import com.bank.reconciliation.service.FraudService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/fraud")
public class FraudController {

    private final FraudService fraudService;
    public FraudController(FraudService fraudService){ this.fraudService = fraudService; }

    @PostMapping("/check")
    public String check(@RequestParam Long paymentId,
                        @RequestParam BigDecimal amount,
                        @RequestParam Long fromAccountId,
                        @RequestParam Long toAccountId) throws Exception {
        fraudService.checkPayment(paymentId, amount, fromAccountId, toAccountId);
        return "OK";
    }
}