package com.bank.auth.controller;

import com.bank.auth.dto.*;
import com.bank.auth.service.AuthService;
//import io.micrometer.tracing.annotation.NewSpan;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }
//    @NewSpan("login")
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        log.info("Starting login with={}",
                request);
        return authService.login(request);
    }
}