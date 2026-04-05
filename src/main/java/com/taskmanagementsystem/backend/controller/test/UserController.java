package com.taskmanagementsystem.backend.controller.test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // Only users with role USER or ADMIN can access this
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "Welcome, USER!";
    }
}