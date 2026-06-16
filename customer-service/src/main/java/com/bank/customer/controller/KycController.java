package com.bank.customer.controller;

import com.bank.customer.dto.request.SubmitKycRequest;
import com.bank.customer.dto.response.KycDocumentResponse;
import com.bank.customer.service.KycService;
import com.core.common.dto.ApiResponseFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "KYC API", description = "KYC management APIs")

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class KycController {

    private final KycService kycService;
    @Operation(summary = "submit customer by id")

    @PostMapping("/customers/{customerId}/kyc")
    public ResponseEntity<ApiResponseFormat<KycDocumentResponse>> submit(
            @PathVariable Long customerId,
            @Valid @RequestBody SubmitKycRequest request
    ) {

        KycDocumentResponse response =
                kycService.submit(customerId, request);

        return ResponseEntity.ok(
                ApiResponseFormat.success(
                        "KYC submitted successfully",
                        response
                )
        );
    }

    @Operation(summary = "approve customer by id")
    @PatchMapping("/kyc/{id}/approve")
    public ResponseEntity<ApiResponseFormat<KycDocumentResponse>> approve(
            @PathVariable Long id
    ) {

        KycDocumentResponse response =
                kycService.approve(id);

        return ResponseEntity.ok(
                ApiResponseFormat.success(
                        "KYC approved successfully",
                        response
                )
        );
    }
    @Operation(summary = "reject customer by id")

    @PatchMapping("/kyc/{id}/reject")
    public ResponseEntity<ApiResponseFormat<KycDocumentResponse>> reject(
            @PathVariable Long id
    ) {

        KycDocumentResponse response =
                kycService.reject(id);

        return ResponseEntity.ok(
                ApiResponseFormat.success(
                        "KYC rejected successfully",
                        response
                )
        );
    }
}