package com.bank.customer.entity;

import com.bank.customer.enums.KycDocumentType;
import com.bank.customer.enums.KycStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "kyc_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KycDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "doc_type", nullable = false, length = 30)
    private KycDocumentType docType;

    @Column(name = "doc_number", nullable = false, length = 100)
    private String docNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private KycStatus status;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @org.hibernate.annotations.CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @org.hibernate.annotations.UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}