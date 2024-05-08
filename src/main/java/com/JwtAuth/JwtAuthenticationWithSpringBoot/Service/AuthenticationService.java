package com.JwtAuth.JwtAuthenticationWithSpringBoot.Service;

import com.JwtAuth.JwtAuthenticationWithSpringBoot.Doa.LoginResponse;
import com.JwtAuth.JwtAuthenticationWithSpringBoot.Models.EmployeeModel;
import com.JwtAuth.JwtAuthenticationWithSpringBoot.utils.jwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private jwtService jwtservice;

    private final String SECRET_KEY = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf"; // Change this to your secret key

    public LoginResponse authenticate(String employeeCode, String password) {
        EmployeeModel employeeDetails = employeeService.getEmployeeDetailsByEmployeeCode(employeeCode, password);

        if (employeeDetails != null && password.equals(employeeDetails.getEmployee_pass())) {
            String acessstoken = jwtservice.generateAccessToken(employeeDetails);
            String refreshtoken = jwtservice.generateRefreshToken(employeeDetails);

            return new LoginResponse(acessstoken , refreshtoken, employeeDetails);
        } else {
            return null;
        }
    }
}
