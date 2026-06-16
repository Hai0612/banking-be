package com.core.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final CoreErrorCode errorCode;

    public BusinessException(CoreErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(
            CoreErrorCode errorCode,
            String message
    ) {
        super(message);
        this.errorCode = errorCode;
    }
}