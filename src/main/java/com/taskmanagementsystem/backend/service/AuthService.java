package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.AuthResponse;
import com.taskmanagementsystem.backend.dto.LoginRequest;
import com.taskmanagementsystem.backend.dto.SignupRequest;

public interface AuthService {
    void signup(SignupRequest request);
    
    AuthResponse login(LoginRequest request);
} 
