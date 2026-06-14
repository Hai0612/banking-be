package com.core.security.filter;

import com.core.security.auth.CustomAuthenticationEntryPoint;
import com.core.security.config.SecurityProperties;
import com.core.security.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;@RequiredArgsConstructor
public class InternalApiKeyFilter
        extends OncePerRequestFilter {

    private final SecurityProperties properties;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String apiKey =
                request.getHeader(
                        SecurityConstants.INTERNAL_API_KEY_HEADER
                );

        if (!Objects.equals(
                apiKey,
                properties.getInternalApiKey()
        )) {

            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid Internal API Key"
            );

            return;
        }

        filterChain.doFilter(
                request,
                response
        );
    }

    @Override
    protected boolean shouldNotFilter(
            HttpServletRequest request
    ) {

        System.out.println("shouldNotFilter " + request.getRequestURI());

        return !request.getRequestURI()
                .contains("/internal/");
    }
}