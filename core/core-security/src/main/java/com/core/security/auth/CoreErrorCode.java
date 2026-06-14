package com.core.security.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Defines standardized error codes for the application.
 * This enum is the single source of truth for all business-level errors.
 * Each enum constant represents a specific error condition with its code,
 * a user-friendly message, and the appropriate HTTP status.
 */
@Getter
@RequiredArgsConstructor
public enum CoreErrorCode implements ApiResponseCode {
    // --- General & Server Errors ---
    INTERNAL_SERVER_ERROR(false,"AVT0001", "error.other_error", HttpStatus.OK),
    VALIDATION_ERROR(false,"AVT0002", "error.input_validation_failed", HttpStatus.OK),
    MALFORMED_REQUEST_BODY(false,"AVT0003", "error.invalid_input", HttpStatus.OK),
    MISSING_REQUEST_PARAMETER(false,"AVT0004", "error.required_parameter", HttpStatus.OK),
    SERVICE_UNAVAILABLE(false,"AVT0005", "error.service_unavailable", HttpStatus.OK),
    PHONE_NUMBER_INVALID(false,"AVT0006", "error.phone_number_invalid", HttpStatus.OK),
    UNAUTHORIZED(false,"AVT0007", "error.unauthorized", HttpStatus.OK),
    MISMATCHING_TOKEN_SUBJECT(false, "AVT0008", "error.token_subject_mismatch", HttpStatus.OK),
    USER_NOT_LINKED(false, "AVT0009", "error.user_not_linked", HttpStatus.OK),
    RESOURCE_NOT_FOUND(false,"AVT0010", "error.resource_not_found", HttpStatus.OK),
    METHOD_NOT_ALLOWED(false,"AVT0011", "error.method_not_allowed", HttpStatus.OK),
    FORBIDDEN(false,"AVT0012", "error.forbidden", HttpStatus.FORBIDDEN),
    TOKEN_ERROR(false,"AVT0012", "error.token_error", HttpStatus.FORBIDDEN),

    ;
    private final boolean success;
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}