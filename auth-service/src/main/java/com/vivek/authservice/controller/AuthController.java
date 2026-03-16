package com.vivek.authservice.controller;

import com.vivek.authservice.dto.LoginRequest;
import com.vivek.authservice.dto.LoginResponse;
import com.vivek.authservice.dto.RegisterRequest;
import com.vivek.authservice.service.AuthServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService)
    {
        this.authService=authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request)
    {
        authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request)
    {
        return authService.login(request);
    }




}
