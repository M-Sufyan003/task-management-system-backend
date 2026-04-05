package com.taskmanagementsystem.backend.controller;

import com.taskmanagementsystem.backend.dto.AuthResponse;
import com.taskmanagementsystem.backend.dto.LoginRequest;
import com.taskmanagementsystem.backend.dto.SignupRequest;
import com.taskmanagementsystem.backend.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map; // <-- Make sure this import is present

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        authService.signup(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.login(request);

        // Build Map for consistency
        Map<String, String> response = new HashMap<>();
        response.put("message", authResponse.getMessage());
        response.put("token", authResponse.getToken());

        return ResponseEntity.ok(response);
    }
}