package com.bank.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan({"com.bank.ledger", "com.bank.common.components"})
@EntityScan({"com.bank.ledger.entity"})
public class LedgerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LedgerServiceApplication.class, args);
    }
}
