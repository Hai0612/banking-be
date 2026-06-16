package com.core.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.MDC;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponseFormat<T>(
        String traceId,
        long timestamp,
        boolean success,
        String code,
        String message,
        T data
) {

    public static <T> ApiResponseFormat<T> success(T data) {

        return new ApiResponseFormat<>(
                MDC.get("traceId"),
                System.currentTimeMillis(),
                true,
                "SUCCESS",
                "Success",
                data
        );
    }

    public static <T> ApiResponseFormat<T> success(
            String message,
            T data
    ) {

        return new ApiResponseFormat<>(
                MDC.get("traceId"),
                System.currentTimeMillis(),
                true,
                "SUCCESS",
                message,
                data
        );
    }

    public static ApiResponseFormat<Void> success(
            String message
    ) {

        return new ApiResponseFormat<>(
                MDC.get("traceId"),
                System.currentTimeMillis(),
                true,
                "SUCCESS",
                message,
                null
        );
    }

    public static ApiResponseFormat<Void> error(
            String code,
            String message
    ) {

        return new ApiResponseFormat<>(
                MDC.get("traceId"),
                System.currentTimeMillis(),
                false,
                code,
                message,
                null
        );
    }

    public static <T> ApiResponseFormat<T> error(
            String code,
            String message,
            T data
    ) {

        return new ApiResponseFormat<>(
                MDC.get("traceId"),
                System.currentTimeMillis(),
                false,
                code,
                message,
                data
        );
    }
}