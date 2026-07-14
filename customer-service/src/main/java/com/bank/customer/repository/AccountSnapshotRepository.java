package com.bank.customer.repository;


import com.bank.customer.entity.AccountSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountSnapshotRepository extends JpaRepository<AccountSnapshot, Long> {
}
