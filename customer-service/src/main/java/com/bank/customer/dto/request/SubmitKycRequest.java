package com.bank.customer.dto.request;

import com.bank.customer.enums.KycDocumentType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubmitKycRequest {

    private KycDocumentType docType;

    @NotBlank
    private String docNumber;
}