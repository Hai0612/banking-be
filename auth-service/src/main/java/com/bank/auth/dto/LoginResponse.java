package com.bank.auth.dto;



public record LoginResponse(
        String accessToken,
        String refreshToken,
        long expiresIn
) {}