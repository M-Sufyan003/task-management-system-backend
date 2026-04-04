package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.SignupRequest;

public interface AuthService {
    void signup(SignupRequest request);
    
} 
