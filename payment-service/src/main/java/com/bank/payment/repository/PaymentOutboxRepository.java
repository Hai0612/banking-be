package com.bank.payment.repository;

import com.bank.payment.entity.Payment;
import com.bank.payment.entity.PaymentIdempotencyKey;
import com.bank.payment.entity.PaymentOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentOutboxRepository
        extends JpaRepository<PaymentOutbox, Long> {
}