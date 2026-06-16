package com.bank.payment.controller;

import com.bank.payment.dto.CreatePaymentRequest;
import com.bank.payment.dto.PaymentCreatedResponse;
import com.bank.payment.dto.PaymentRequest;
import com.bank.payment.dto.PaymentResponse;
import com.bank.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/transfers")
    public PaymentCreatedResponse create(
            @RequestBody
            CreatePaymentRequest request
    ) {

        return paymentService.create(
                request
        );
    }
}