package com.core.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Defines standardized error codes for the application.
 * This enum is the single source of truth for all business-level errors.
 */
@Getter
@RequiredArgsConstructor
public enum CoreErrorCode implements ApiResponseCode {

    // ==========================================================
    // General Errors
    // ==========================================================

    INTERNAL_SERVER_ERROR(
            false,
            "AVT0001",
            "error.other_error",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    VALIDATION_ERROR(
            false,
            "AVT0002",
            "error.input_validation_failed",
            HttpStatus.BAD_REQUEST
    ),

    MALFORMED_REQUEST_BODY(
            false,
            "AVT0003",
            "error.invalid_input",
            HttpStatus.BAD_REQUEST
    ),

    MISSING_REQUEST_PARAMETER(
            false,
            "AVT0004",
            "error.required_parameter",
            HttpStatus.BAD_REQUEST
    ),

    SERVICE_UNAVAILABLE(
            false,
            "AVT0005",
            "error.service_unavailable",
            HttpStatus.SERVICE_UNAVAILABLE
    ),

    PHONE_NUMBER_INVALID(
            false,
            "AVT0006",
            "error.phone_number_invalid",
            HttpStatus.BAD_REQUEST
    ),

    UNAUTHORIZED(
            false,
            "AVT0007",
            "error.unauthorized",
            HttpStatus.UNAUTHORIZED
    ),

    MISMATCHING_TOKEN_SUBJECT(
            false,
            "AVT0008",
            "error.token_subject_mismatch",
            HttpStatus.UNAUTHORIZED
    ),

    USER_NOT_LINKED(
            false,
            "AVT0009",
            "error.user_not_linked",
            HttpStatus.BAD_REQUEST
    ),

    RESOURCE_NOT_FOUND(
            false,
            "AVT0010",
            "error.resource_not_found",
            HttpStatus.NOT_FOUND
    ),

    METHOD_NOT_ALLOWED(
            false,
            "AVT0011",
            "error.method_not_allowed",
            HttpStatus.METHOD_NOT_ALLOWED
    ),

    FORBIDDEN(
            false,
            "AVT0012",
            "error.forbidden",
            HttpStatus.FORBIDDEN
    ),

    TOKEN_ERROR(
            false,
            "AVT0013",
            "error.token_error",
            HttpStatus.UNAUTHORIZED
    ),

    // ==========================================================
    // Customer Domain
    // ==========================================================

    CUSTOMER_NOT_FOUND(
            false,
            "CUS0001",
            "error.customer_not_found",
            HttpStatus.NOT_FOUND
    ),

    CUSTOMER_ALREADY_EXISTS(
            false,
            "CUS0002",
            "error.customer_already_exists",
            HttpStatus.CONFLICT
    ),

    EMAIL_ALREADY_EXISTS(
            false,
            "CUS0003",
            "error.email_already_exists",
            HttpStatus.CONFLICT
    ),

    PHONE_ALREADY_EXISTS(
            false,
            "CUS0004",
            "error.phone_already_exists",
            HttpStatus.CONFLICT
    ),

    INVALID_CUSTOMER_STATUS(
            false,
            "CUS0005",
            "error.invalid_customer_status",
            HttpStatus.BAD_REQUEST
    ),

    // ==========================================================
    // KYC Domain
    // ==========================================================

    KYC_NOT_FOUND(
            false,
            "KYC0001",
            "error.kyc_not_found",
            HttpStatus.NOT_FOUND
    ),

    KYC_ALREADY_APPROVED(
            false,
            "KYC0002",
            "error.kyc_already_approved",
            HttpStatus.BAD_REQUEST
    ),

    KYC_ALREADY_REJECTED(
            false,
            "KYC0003",
            "error.kyc_already_rejected",
            HttpStatus.BAD_REQUEST
    ),

    KYC_DOCUMENT_DUPLICATED(
            false,
            "KYC0004",
            "error.kyc_document_duplicated",
            HttpStatus.CONFLICT
    ),

    KYC_DOCUMENT_EXPIRED(
            false,
            "KYC0005",
            "error.kyc_document_expired",
            HttpStatus.BAD_REQUEST
    );

    private final boolean success;
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}