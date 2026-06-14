package com.core.security.auth;

import java.util.Set;
import java.util.UUID;

public record AppUserPrincipal(
        UUID userId,
        String username,
        Set<String> roles
) {
}