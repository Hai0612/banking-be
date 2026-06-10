package com.core.security.exception;

import org.springframework.http.HttpStatus;

/**
 * A common interface for all application error codes,
 * both core and service-specific.
 */
public interface ApiResponseCode {
    boolean isSuccess();
    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}