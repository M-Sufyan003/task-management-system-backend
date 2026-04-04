package com.taskmanagementsystem.backend.controller;

import com.taskmanagementsystem.backend.dto.SignupRequest;
import com.taskmanagementsystem.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return "User registered successfully";
    }
}

