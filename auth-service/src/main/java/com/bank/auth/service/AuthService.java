package com.bank.auth.service;



import com.bank.auth.dto.*;
import com.bank.auth.entity.RefreshToken;
import com.bank.auth.repository.*;
import com.bank.auth.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {

        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = encoder.encode("123456");

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        var accessToken = jwtService.generateAccessToken(user);

        var refreshTokenValue = UUID.randomUUID().toString();

        var refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenValue);
        refreshToken.setUser(user);
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(7));
        refreshToken.setRevoked(false);
        refreshToken.setCreatedAt(LocalDateTime.now());

        log.info("Created refresh token: {}", refreshToken);
        refreshTokenRepository.save(refreshToken);

        return new LoginResponse(
                accessToken,
                refreshTokenValue,
                900
        );
    }
}