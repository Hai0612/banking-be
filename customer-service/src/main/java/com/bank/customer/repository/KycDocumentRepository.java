package com.bank.customer.repository;


import com.bank.customer.entity.KycDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KycDocumentRepository
        extends JpaRepository<KycDocument, Long> {

    List<KycDocument> findByCustomerId(Long customerId);
}