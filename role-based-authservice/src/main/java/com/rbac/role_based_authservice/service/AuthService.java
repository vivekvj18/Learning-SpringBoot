package com.rbac.role_based_authservice.service;

import com.rbac.role_based_authservice.dto.AuthResponse;
import com.rbac.role_based_authservice.dto.LoginRequest;
import com.rbac.role_based_authservice.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
