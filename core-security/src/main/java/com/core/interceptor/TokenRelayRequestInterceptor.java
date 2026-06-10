package com.core.security.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Tầng: OUTBOUND HTTP (khi service gọi service khác)
 * Khi dùng feign
 */
public class TokenRelayRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        // 1. Get the current HTTP Request directly from Servlet context
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            // 2. Extract the Authorization header manually
            String authHeader = request.getHeader(AUTHORIZATION_HEADER);

            // 3. If present, pass it along to Feign
            if (authHeader != null && !authHeader.isBlank()) {
                template.header(AUTHORIZATION_HEADER, authHeader);
            }
        }
    }
}