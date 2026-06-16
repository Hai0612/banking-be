package com.bank.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.customer.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);
}