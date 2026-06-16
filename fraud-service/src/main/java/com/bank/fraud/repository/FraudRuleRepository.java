package com.bank.reconciliation.repository;

import com.bank.reconciliation.entity.FraudRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRuleRepository extends JpaRepository<FraudRule, Long> {}
