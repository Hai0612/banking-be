package com.bank.customer.mapper;

import com.bank.customer.dto.response.KycDocumentResponse;
import com.bank.customer.entity.KycDocument;

public final class KycMapper {

    private KycMapper() {
    }

    public static KycDocumentResponse toResponse(
            KycDocument doc
    ) {

        return new KycDocumentResponse(
                doc.getId(),
                doc.getCustomerId(),
                doc.getDocType(),
                doc.getDocNumber(),
                doc.getStatus(),
                doc.getSubmittedAt(),
                doc.getVerifiedAt()
        );
    }
}