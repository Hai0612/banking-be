package com.bank.payment.service;

import com.bank.payment.dto.*;
import com.bank.payment.entity.Payment;
import com.bank.payment.entity.PaymentIdempotencyKey;
import com.bank.payment.entity.PaymentOutbox;
import com.bank.payment.enums.OutboxStatus;
import com.bank.payment.enums.PaymentStatus;
import com.bank.payment.repository.PaymentIdempotencyRepository;
import com.bank.payment.repository.PaymentOutboxRepository;
import com.bank.payment.repository.PaymentRepository;
//import com.bank.payment.outbox.OutboxPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentIdempotencyRepository
            idempotencyRepository;

    private final PaymentOutboxRepository
            outboxRepository;

    private final ObjectMapper objectMapper;

    public PaymentCreatedResponse create(
            CreatePaymentRequest request
    ) {

        Optional<PaymentIdempotencyKey> existing =
                idempotencyRepository.findByIdempotencyKey(
                        request.idempotencyKey()
                );

        if (existing.isPresent()) {

            Payment payment =
                    paymentRepository.findById(
                            existing.get().getPaymentId()
                    ).orElseThrow();

            return new PaymentCreatedResponse(
                    payment.getTransactionId(),
                    payment.getId(),
                    payment.getStatus().name(),
                    payment.getCreatedAt()
            );
        }

        UUID txId = UUID.randomUUID();

        Payment payment =
                new Payment();

        payment.setTransactionId(txId);

        payment.setFromAccountId(
                request.fromAccountId());

        payment.setToAccountId(
                request.toAccountId());

        payment.setAmount(
                request.amount());

        payment.setCurrency(
                request.currency());

        payment.setStatus(
                PaymentStatus.PENDING);

        Payment saved =
                paymentRepository.save(payment);

        createOutbox(saved);

        createIdempotency(
                request.idempotencyKey(),
                saved
        );

        return new PaymentCreatedResponse(
                saved.getTransactionId(),
                saved.getId(),
                saved.getStatus().name(),
                saved.getCreatedAt()
        );

    }
    private void createIdempotency(
            String key,
            Payment payment
    ) {

        PaymentIdempotencyKey entity =
                new PaymentIdempotencyKey();

        entity.setIdempotencyKey(key);

        entity.setPaymentId(
                payment.getId());

        entity.setStatus(
                payment.getStatus().name());

        entity.setResponse(
                Map.of(
                        "paymentId",
                        payment.getId()
                )
        );

        idempotencyRepository.save(entity);
    }
    private void createOutbox(
            Payment payment
    ) {

        try {

            PaymentOutbox outbox =
                    new PaymentOutbox();

            outbox.setTransactionId(
                    payment.getTransactionId());

            outbox.setPaymentId(
                    payment.getId());

            outbox.setEventType(
                    "PAYMENT_CREATED");

            outbox.setStatus(
                    OutboxStatus.PENDING);

            outbox.setPayload(
                    Map.of(
                            "paymentId", payment.getId(),
                            "transactionId", payment.getTransactionId()
                    )
            );

            outboxRepository.save(outbox);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}