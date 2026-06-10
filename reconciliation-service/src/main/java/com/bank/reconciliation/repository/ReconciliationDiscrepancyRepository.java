package com.bank.fraud.repository;

import com.bank.fraud.entity.ReconciliationDiscrepancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReconciliationDiscrepancyRepository extends JpaRepository<ReconciliationDiscrepancy, Long> {
    List<ReconciliationDiscrepancy> findByReconciliationRunId(Long runId);
}