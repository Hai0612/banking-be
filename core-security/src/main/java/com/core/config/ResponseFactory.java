package com.core.security.config;

import com.core.security.dto.ApiResponseFormat;
import com.core.security.exception.ApiResponseCode;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;

import java.util.Locale;
import java.util.Optional;

@Slf4j
public class ResponseFactory {

    private final Tracer tracer;
    private final MessageSource messageSource;

    // Inject the Tracer via the constructor
    public ResponseFactory(Tracer tracer, MessageSource messageSource) {
        this.tracer = tracer;
        this.messageSource = messageSource;
    }
//
//    public <T> ApiResponseFormat<T> success(T data) {
//        return new ApiResponseFormat<>(getCurrentTraceId(), System.currentTimeMillis(), true, null, data);
//    }
//
//    public <T> ApiResponseFormat<T> success(T data, String message) {
//        return new ApiResponseFormat<>(getCurrentTraceId(), System.currentTimeMillis(), true, message, data);
//    }

    public <T> ResponseEntity<ApiResponseFormat<T>> response(ApiResponseCode responseCode, T data) {
        // Get the current request's locale
        Locale locale = LocaleContextHolder.getLocale();

        // Resolve the message key to get the localized message
        String localizedMessage = messageSource.getMessage(
                responseCode.getMessage(), // e.g., "success.user.exists"
                null,
                locale
        );

        // Create the final response body
        ApiResponseFormat<T> body = new ApiResponseFormat<>(
                getCurrentTraceId(),
                System.currentTimeMillis(),
                responseCode.isSuccess(),
                responseCode.getCode(),
                localizedMessage,
                data // Preserves T
        );

        // Return the full ResponseEntity with the correct status code
        return new ResponseEntity<>(body, responseCode.getHttpStatus());
    }

    public <T> ResponseEntity<ApiResponseFormat<T>> typedResponse(ApiResponseCode responseCode, T data) {
        Locale locale = LocaleContextHolder.getLocale();
        String localizedMessage = messageSource.getMessage(responseCode.getMessage(), null, locale);

        ApiResponseFormat<T> body = new ApiResponseFormat<>(
                getCurrentTraceId(),
                System.currentTimeMillis(),
                responseCode.isSuccess(),
                responseCode.getCode(),
                localizedMessage,
                data // Preserves T
        );

        return new ResponseEntity<>(body, responseCode.getHttpStatus());
    }

    public ApiResponseFormat<Object> response(ApiResponseFormat.ApiData data) {
        Locale locale = LocaleContextHolder.getLocale();
        String resolvedMessage = this.messageSource.getMessage(
                data.message(),
                (Object[]) null,
                data.message(), // <-- Default message if code not found
                locale
        );

        return new ApiResponseFormat(
                getCurrentTraceId(),
                System.currentTimeMillis(),
                data.success(),
                data.code(),
                resolvedMessage,
                data.details()
        );
    }

    public ApiResponseFormat<Object> response(ApiResponseFormat.ApiData data, Object[] details) {
        Locale locale = LocaleContextHolder.getLocale();
        String resolvedMessage = this.messageSource.getMessage(
                data.message(),
                details,
                data.message(), // <-- Default message if code not found
                locale
        );
        log.info("Response message: {}", resolvedMessage);
        return new ApiResponseFormat(
                getCurrentTraceId(),
                System.currentTimeMillis(),
                data.success(),
                data.code(),
                resolvedMessage,
                data.details()
        );
    }

    /**
     * Retrieves the current traceId from the active Span.
     * Returns null if no active trace exists.
     */
    private String getCurrentTraceId() {
        return Optional.ofNullable(tracer.currentSpan())
                .map(Span::context)
                .map(TraceContext::traceId)
                .orElse(null);
    }
}