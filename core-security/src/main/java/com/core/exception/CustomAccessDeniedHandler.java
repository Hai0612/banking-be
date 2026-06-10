package com.core.security.exception;

import com.core.security.auth.AppUserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
// Replace with your actual package imports for ApiResponseFormat and CoreErrorCode
import com.core.security.config.ResponseFactory;
import com.core.security.dto.ApiResponseFormat;

import com.core.security.exception.CoreErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 *
 * 403 Forbidden
 * Đăng nhập rồi
 * Token hợp lệ rồi
 * Nhưng không đủ quyền
 * @PreAuthorize("hasRole('ADMIN')") Khi không đủ quyền thì spring security sẽ gọi AccessDeniedHandler để trả về lỗi 403 Forbidden
 * đó là lý do tại sao kế thừa AccessDeniedHandler và override method handle để custom response trả về cho client
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;
    private final ResponseFactory responseFactory;

    public CustomAccessDeniedHandler(ObjectMapper objectMapper, ResponseFactory responseFactory) {
        this.objectMapper = objectMapper;
        this.responseFactory = responseFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        // 1. Extract context for detailed logging
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentAzp = "UNKNOWN";
        String currentUser = "UNKNOWN";

        if (auth != null && auth.getDetails() instanceof AppUserPrincipal principal) {
            currentAzp = principal.username();
            currentUser = principal.username();
        }

        // 2. Log exactly WHO failed and WHY
        log.error("🔴 403 Forbidden at [{}] | User: [{}] | AZP: [{}] | Reason: {}",
                request.getRequestURI(), currentUser, currentAzp, accessDeniedException.getMessage());

        // 3. Set the HTTP Response headers
        CoreErrorCode errorCode = CoreErrorCode.FORBIDDEN;
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 4. Create your ApiData using your CoreErrorCode
        ApiResponseFormat.ApiData<Object> apiData = new ApiResponseFormat.ApiData<>(
                errorCode.isSuccess(),
                errorCode.getCode(),
                errorCode.getMessage(), // Ensure this key exists in your message.properties
                null
        );

        // 5. Pass it through your factory to get i18n, traceId, and timestamp!
        ApiResponseFormat<Object> finalResponse = responseFactory.response(apiData);

        // 6. Write the final formatted response directly to the output stream
        objectMapper.writeValue(response.getWriter(), finalResponse);
    }
}