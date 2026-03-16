package com.rbac.role_based_authservice.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
