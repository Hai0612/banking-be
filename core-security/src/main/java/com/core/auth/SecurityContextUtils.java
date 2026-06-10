package com.core.security.auth;

import com.core.security.exception.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public final class SecurityContextUtils {

    private SecurityContextUtils() {
    }

    public static Optional<AppUserPrincipal> getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof AppUserPrincipal principal) {
            return Optional.of(principal);
        }

        return Optional.empty();
    }

    public static AppUserPrincipal requireCurrentUser() {
        return getCurrentUser()
                .orElseThrow(() -> new ApiException(CoreErrorCode.UNAUTHORIZED));
    }
    public static UUID getCurrentUserId() {
        return requireCurrentUser().userId();
    }

    public static String getCurrentUsername() {
        return requireCurrentUser().username();
    }
}