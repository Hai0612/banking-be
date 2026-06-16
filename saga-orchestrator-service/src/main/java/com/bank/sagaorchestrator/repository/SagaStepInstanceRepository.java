package com.bank.sagaorchestrator.repository;

import com.bank.sagaorchestrator.constants.SagaConstants;
import com.bank.sagaorchestrator.entity.SagaInstance;
import com.bank.sagaorchestrator.entity.SagaStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SagaStepInstanceRepository extends JpaRepository<SagaStep, Long> {
    Optional<SagaStep> findFirstBySagaInstanceAndStepNameAndStatusOrderByCreatedAtDesc(
            SagaInstance sagaInstance, String stepName, SagaConstants.SagaStepStatus status);
    
    Optional<SagaStep> findFirstBySagaInstanceAndStepNameOrderByCreatedAtDesc(
            SagaInstance sagaInstance, String stepName);
}
