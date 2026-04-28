package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.SignupRequest;
import com.taskmanagementsystem.backend.entity.Role;
import com.taskmanagementsystem.backend.entity.User;
import com.taskmanagementsystem.backend.exception.UserAlreadyExistsException;
import com.taskmanagementsystem.backend.repository.UserRepository;
import com.taskmanagementsystem.backend.util.JwtUtil;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.taskmanagementsystem.backend.dto.AuthResponse;
import com.taskmanagementsystem.backend.dto.LoginRequest;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void signup(SignupRequest request) {
        // Step 1: Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");

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

    @Override
    public AuthResponse login(LoginRequest request) {

        // 1. Find user by email
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        User user = optionalUser.get();

        // 2. Compare passwords
        boolean isMatch = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!isMatch) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(
                "Login successful",
                token);
    }
}
