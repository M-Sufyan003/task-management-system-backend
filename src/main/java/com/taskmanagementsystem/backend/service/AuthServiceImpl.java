package com.taskmanagementsystem.backend.service;

import com.taskmanagementsystem.backend.dto.SignupRequest;
import com.taskmanagementsystem.backend.entity.Role;
import com.taskmanagementsystem.backend.entity.User;
import com.taskmanagementsystem.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public void signup(SignupRequest request)
    {
        // Step 1: Check if email already exists
        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new RuntimeException("Email already exists");
            
        }
        // Step 2: Create User object
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Password hashing will be added next step
        user.setPassword(request.getPassword());

        // Step 3: Assign default role
        user.setRole(Role.USER);

        // Step 4: Save to DB
        userRepository.save(user);

    }
}
