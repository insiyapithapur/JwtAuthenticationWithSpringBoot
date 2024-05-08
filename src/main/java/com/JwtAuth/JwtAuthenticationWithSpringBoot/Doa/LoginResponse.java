package com.JwtAuth.JwtAuthenticationWithSpringBoot.Doa;

import com.JwtAuth.JwtAuthenticationWithSpringBoot.Models.EmployeeModel;
import lombok.Data;

@Data
public class LoginResponse {
    private String acesstoken;
    private EmployeeModel employeeDetails;
    private String refreshtoken;

    public LoginResponse(String acesstoken, String refreshtoken , EmployeeModel employeeDetails) {
        this.acesstoken = acesstoken;
        this.employeeDetails = employeeDetails;
        this.refreshtoken = refreshtoken;
    }
}
