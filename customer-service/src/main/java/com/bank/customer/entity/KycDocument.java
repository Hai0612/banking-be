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

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private KycDocumentType docType;

    private String docNumber;

    @Enumerated(EnumType.STRING)
    private KycStatus status;

    private LocalDateTime submittedAt;

    private LocalDateTime verifiedAt;
}