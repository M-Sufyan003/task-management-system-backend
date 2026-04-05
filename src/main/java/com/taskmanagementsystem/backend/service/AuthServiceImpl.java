package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.SignupRequest;
import com.taskmanagementsystem.backend.entity.Role;
import com.taskmanagementsystem.backend.entity.User;
import com.taskmanagementsystem.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signup(SignupRequest request) {
        // Step 1: Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");

        }
        // Step 2: Create User object
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Password hashing is being applied before saving
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);

        // Step 3: Assign default role
        user.setRole(Role.USER);

        // Step 4: Save to DB
        userRepository.save(user);

    }
}
