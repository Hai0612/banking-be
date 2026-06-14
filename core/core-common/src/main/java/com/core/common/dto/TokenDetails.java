package com.core.dto;

import java.util.Optional;

/**
 * Immutable record to hold token-related context data.
 */
public record TokenDetails(
    Optional<String> phoneNumber,
    Optional<String> azp,
    Optional<String> jti,
    Optional<String> scope
) {}