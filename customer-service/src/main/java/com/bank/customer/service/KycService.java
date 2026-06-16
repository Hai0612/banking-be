package com.bank.customer.service;

import com.bank.customer.dto.request.SubmitKycRequest;
import com.bank.customer.dto.response.KycDocumentResponse;
import com.bank.customer.entity.KycDocument;
import com.bank.customer.enums.KycStatus;
import com.bank.customer.mapper.KycMapper;
import com.bank.customer.repository.CustomerRepository;
import com.bank.customer.repository.KycDocumentRepository;
import com.core.common.exception.BusinessException;
import com.core.common.exception.CoreErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class KycService {

    private final CustomerRepository customerRepository;
    private final KycDocumentRepository kycRepository;

    public KycDocumentResponse submit(
            Long customerId,
            SubmitKycRequest request
    ) {

        customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new BusinessException(
                                CoreErrorCode.CUSTOMER_NOT_FOUND
                        )
                );

        KycDocument doc =
                KycDocument.builder()
                        .customerId(customerId)
                        .docType(request.getDocType())
                        .docNumber(request.getDocNumber())
                        .status(KycStatus.PENDING)
                        .submittedAt(LocalDateTime.now())
                        .build();

        doc = kycRepository.save(doc);

        return KycMapper.toResponse(doc);
    }

    public KycDocumentResponse approve(
            Long documentId
    ) {

        KycDocument doc =
                kycRepository.findById(documentId)
                        .orElseThrow(
                                () -> new BusinessException(
                                        CoreErrorCode.KYC_NOT_FOUND
                                )
                        );

        doc.setStatus(KycStatus.APPROVED);
        doc.setVerifiedAt(LocalDateTime.now());

        return KycMapper.toResponse(doc);
    }

    public KycDocumentResponse reject(
            Long documentId
    ) {

        KycDocument doc =
                kycRepository.findById(documentId)
                        .orElseThrow(
                                () -> new BusinessException(
                                        CoreErrorCode.KYC_NOT_FOUND
                                )
                        );

        doc.setStatus(KycStatus.REJECTED);
        doc.setVerifiedAt(LocalDateTime.now());

        return KycMapper.toResponse(doc);
    }
}