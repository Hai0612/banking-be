package com.core.security.filter;

import com.core.security.exception.CustomAuthenticationEntryPoint;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * bảo vệ các API nội bộ giữa các microservice bằng API Key.
 */
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiKeyFilter.class);

    private final String internalApiKey;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    public ApiKeyFilter(String internalApiKey, CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.internalApiKey = internalApiKey;
        this.authenticationEntryPoint = authenticationEntryPoint;


    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String requestApiKey = request.getHeader("X-Internal-API-Key");

        // TRACE: Log the expected key exactly as it was injected at startup
        // WARNING: Remove or mask this in production!
        log.info("Expected Internal API Key: [{}]", this.internalApiKey);
        // TRACE: Log what is actually being extracted from the incoming request header
        log.info("Incoming request to URI [{}]. Received X-Internal-API-Key header: [{}]",
                request.getRequestURI(), requestApiKey);

        if (Objects.equals(this.internalApiKey, requestApiKey)) {
            log.debug("API Key validation successful for URI: [{}]", request.getRequestURI());
            // Key is valid, allow the request to proceed to the controller
            filterChain.doFilter(request, response);
        } else {
            // TRACE: Explicitly log the mismatch side-by-side
            log.error("API Key Mismatch on URI [{}]! Expected: [{}], Received: [{}]",
                    request.getRequestURI(), this.internalApiKey, requestApiKey);

            // Key is invalid. Use your centralized EntryPoint to return the standard ApiResponseFormat!
            authenticationEntryPoint.commence(request, response,
                    new BadCredentialsException("Invalid or Missing Internal API Key"));
        }
    }
}