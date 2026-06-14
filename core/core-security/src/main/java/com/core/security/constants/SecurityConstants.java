package com.core.security.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SecurityConstants {

    private SecurityConstants() {}

    public static final String[] DEFAULT_ALLOWED_AZPS = {
            "AZP_super-app-client",
            "AZP_webapp-client"
    };
    public static final String HEADER_USER_ID = "X-User-Id";
    public static final String HEADER_ROLES = "X-Roles";
    public static final String HEADER_EMAIL = "X-User-Email";
    public static final String HEADER_TRACE_ID = "X-Trace-Id";
    public static final String TOKEN_PREFIX = "X-Trace-Id";

    // Token
    public static final String AUTH_HEADER = "Authorization";

    // Claims
    public static final String CLAIM_ROLES = "roles";
    public static final String CLAIM_EMAIL = "email";
    public static final String[] GLOBAL_PUBLIC_ENDPOINTS = {
            "/management/health/**",
            "/actuator/health/**",
            "/v3/api-docs/**",
            "/api-docs/**",
            "/api-docs",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**"
    };
    public static final String AUTHORIZATION_HEADER =
            "Authorization";

    public static final String BEARER_PREFIX =
            "Bearer ";

    public static final String INTERNAL_API_KEY_HEADER =
            "X-Internal-Api-Key";

    public static final String TRACE_ID_HEADER =
            "X-Trace-Id";


}