package com.vivek.authservice.service;

import com.vivek.authservice.dto.LoginRequest;
import com.vivek.authservice.dto.LoginResponse;
import com.vivek.authservice.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
