package com.core.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.core.security.config.ResponseFactory;
import com.core.security.dto.ApiResponseFormat;

import com.core.security.exception.CoreErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * Xử lý lôi 401 Unauthorized khi người dùng chưa đăng nhập hoặc token không hợp lệ.
 * Chưa đăng nhập
 * Token sai
 * Token hết hạn
 * Token không hợp lệ
 */
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    private final ResponseFactory responseFactory;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper, ResponseFactory responseFactory) {
        this.objectMapper = objectMapper;
        this.responseFactory = responseFactory;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        // 1. Log the exact error
        log.error("🔴 401 Unauthorized at [{}] | Reason: {}", request.getRequestURI(), authException.getMessage());

        // 2. Set the HTTP Response headers
        CoreErrorCode errorCode = CoreErrorCode.UNAUTHORIZED;
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 3. Create your ApiData using your CoreErrorCode
        ApiResponseFormat.ApiData<Object> apiData = new ApiResponseFormat.ApiData<>(
                false,
                errorCode.getCode(),
                errorCode.getMessage(), // Ensure this key exists in your message.properties
                null
        );

        // 4. Pass it through your factory to get i18n, traceId, and timestamp!
        ApiResponseFormat<Object> finalResponse = responseFactory.response(apiData);

        // 5. Write the final formatted response directly to the output stream
        objectMapper.writeValue(response.getWriter(), finalResponse);
    }
}