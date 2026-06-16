package com.bank.customer.dto.response;

import com.bank.customer.enums.KycDocumentType;
import com.bank.customer.enums.KycStatus;

import java.time.LocalDateTime;

public record KycDocumentResponse(

        Long id,

        Long customerId,

        KycDocumentType docType,

        String docNumber,

        KycStatus status,

        LocalDateTime submittedAt,

        LocalDateTime verifiedAt

) {
}