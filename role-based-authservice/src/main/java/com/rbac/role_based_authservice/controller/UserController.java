package com.rbac.role_based_authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String getProfile(Authentication authentication) {

        String email = authentication.getName();

        return "Profile data for user: " + email;
    }
}