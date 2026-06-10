package com.bank.sagaorchestrator.controller;

import com.bank.common.entity.Payment;
import com.bank.common.entity.User;
import com.bank.common.util.SecurityUtil;
import com.bank.sagaorchestrator.entity.SagaInstance;
import com.bank.sagaorchestrator.saga.payment.PaymentProcessingSaga;
import com.bank.sagaorchestrator.saga.payment.PaymentRequest;
import com.bank.sagaorchestrator.saga.useronboarding.UserOnboardingSaga;
import com.bank.sagaorchestrator.service.SagaStateManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/saga")
@RequiredArgsConstructor
public class SagaController {

    private final UserOnboardingSaga userOnboardingSaga;
    private final PaymentProcessingSaga paymentProcessingSaga;
    private final SagaStateManager sagaStateManager;

    @GetMapping("/instances")
    @PreAuthorize("hasRole(T(com.bank.common.AppConstants).ROLE_BAAS_ADMIN)")
    public ResponseEntity<List<SagaInstance>> getAllSagaInstances() {
        log.info("Received request to get all saga instances");

        List<SagaInstance> sagaInstances = sagaStateManager.getAllSagaInstances();

        log.info("Retrieved {} saga instances", sagaInstances.size());
        return ResponseEntity.ok(sagaInstances);
    }

    @PostMapping("/start/user-onboarding")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> startUserOnboardingSaga() {
        log.info("Received request to start user onboarding saga");

        User user = new User();
        user.setUsername(SecurityUtil.getCurrentUsername());
        user.setRoles(SecurityUtil.extractRolesFromJwt());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
            user.setEmail(jwt.getClaimAsString("email"));
            user.setFullName(jwt.getClaimAsString("name"));
        }

        // Use Saga interface to start saga with payload - this will automatically trigger the first command
        SagaInstance sagaInstance = userOnboardingSaga.startSaga(user);

        log.info("User onboarding saga {} started for user: {}", sagaInstance.getId(), user);
        return ResponseEntity.accepted().body("User onboarding process started with saga ID: " + sagaInstance.getId());
    }


    // Endpoint to start payment processing saga
    @PostMapping("/start/payment-processing")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> startPaymentProcessingSaga(@RequestBody PaymentRequest paymentRequest) {
        log.info("Received request to start payment processing saga");

        Payment payment = new Payment();
        payment.setSourceAccountNumber(paymentRequest.getSourceAccountNumber());
        payment.setDestinationAccountNumber(paymentRequest.getDestinationAccountNumber());
        payment.setAmount(paymentRequest.getAmount());
        payment.setDescription(paymentRequest.getDescription());
        payment.setCreatedBy(SecurityUtil.getCurrentUsername());

        SagaInstance sagaInstance = paymentProcessingSaga.startSaga(payment);

        log.info("Payment processing saga {} started", sagaInstance);
        return ResponseEntity.accepted().body("Payment processing started with saga ID: " + sagaInstance.getId());
    }
}
