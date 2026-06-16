package com.bank.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan({"com.bank.fraud", "com.bank.common.components"})
@EntityScan({"com.bank.common.entity"})
public class FraudServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FraudServiceApplication.class, args);
    }
}
