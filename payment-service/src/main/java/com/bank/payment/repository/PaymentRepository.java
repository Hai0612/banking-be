package com.bank.payment.repository;

import com.bank.payment.entity.Payment;
import com.bank.payment.entity.PaymentIdempotencyKey;
import com.bank.payment.entity.PaymentOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(
            UUID transactionId
    );
}