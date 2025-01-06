package com.product.manage.controller;

import com.product.manage.dto.AuthRequest;
import com.product.manage.dto.AuthResponse;
import com.product.manage.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/account")
    public ResponseEntity<String> createAccount(@RequestBody AuthRequest authRequest) {
        authService.createAccount(authRequest);
        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.generateToken(authRequest));
    }
}