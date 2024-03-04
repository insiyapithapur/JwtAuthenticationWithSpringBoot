package com.JwtAuth.JwtAuthenticationWithSpringBoot.Doa;

import lombok.Data;

@Data
public class LoginRequest {
    private String employeeCode;
    private String password;
}
