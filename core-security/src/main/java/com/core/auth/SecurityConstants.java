package com.core.security.auth;
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

    // Token
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

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

    /**
     * Dynamically builds the complete list of public endpoints for a microservice.
     *
     * @param serviceName The value of ${spring.application.name}
     * @return An array containing original globals, prefixed globals, and custom health routes.
     */
    public static String[] buildDynamicPublicEndpoints(String serviceName) {
        List<String> allEndpoints = new ArrayList<>();

        // 1. Add the original raw endpoints (Useful for internal K8s checks)
        allEndpoints.addAll(Arrays.asList(GLOBAL_PUBLIC_ENDPOINTS));

        if (serviceName != null && !serviceName.isBlank()) {
            // 2. Add the Gateway-prefixed Swagger endpoints
            for (String path : GLOBAL_PUBLIC_ENDPOINTS) {
                allEndpoints.add("/" + serviceName + path);
            }

            // 3. Add your custom dynamic health endpoint!
            allEndpoints.add("/api/v1/" + serviceName + "/health");
        }

        return allEndpoints.toArray(new String[0]);
    }
}