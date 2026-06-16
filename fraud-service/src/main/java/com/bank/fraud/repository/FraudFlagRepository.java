package com.bank.reconciliation.repository;

import com.bank.reconciliation.entity.FraudFlag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudFlagRepository extends JpaRepository<FraudFlag, Long> {
    List<FraudFlag> findByPaymentId(Long paymentId);
}