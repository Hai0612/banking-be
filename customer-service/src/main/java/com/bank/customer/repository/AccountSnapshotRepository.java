package com.bank.customer.repository;


import com.bank.account.entity.AccountSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountSnapshotRepository extends JpaRepository<AccountSnapshot, Long> {
}
