package com.bank.reconciliation.service;

import com.bank.reconciliation.dto.PaymentContext;
import com.bank.reconciliation.entity.FraudRule;
import com.bank.reconciliation.entity.FraudFlag;
import com.bank.reconciliation.repository.*;
import com.bank.reconciliation.rules.AmountFraudStrategy;
import com.bank.reconciliation.rules.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FraudService {

    private final FraudRuleRepository ruleRepo;
    private final FraudFlagRepository flagRepo;
    private final ObjectMapper mapper = new ObjectMapper();
    private final FraudStrategy amountStrategy = new AmountFraudStrategy(); // pluggable

    public FraudService(FraudRuleRepository ruleRepo, FraudFlagRepository flagRepo){
        this.ruleRepo = ruleRepo; this.flagRepo = flagRepo;
    }

    @Transactional
    public void checkPayment(Long paymentId, BigDecimal amount, Long fromAccount, Long toAccount) throws Exception {
        List<FraudRule> rules = ruleRepo.findAll();
        PaymentContext ctx = new PaymentContext(amount, fromAccount, toAccount);

        for(FraudRule rule : rules){
            JsonNode cond = mapper.readTree(rule.getCondition());
            boolean matched = amountStrategy.evaluate(cond, ctx);
            if(matched){
                FraudFlag flag = new FraudFlag(paymentId, rule.getId());
                flagRepo.save(flag);

                // fake alert (could be Kafka / email / SMS)
                System.out.printf("ALERT: Payment %d flagged by rule %s%n", paymentId, rule.getName());

                if("BLOCK".equalsIgnoreCase(rule.getAction())){
                    throw new RuntimeException("Payment blocked by rule: " + rule.getName());
                }
            }
        }
    }
}