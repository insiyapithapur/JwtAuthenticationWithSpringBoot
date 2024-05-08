package com.JwtAuth.JwtAuthenticationWithSpringBoot;

import com.JwtAuth.JwtAuthenticationWithSpringBoot.Doa.LoginRequest;
import com.JwtAuth.JwtAuthenticationWithSpringBoot.Doa.LoginResponse;
import com.JwtAuth.JwtAuthenticationWithSpringBoot.Models.EmployeeModel;
import com.JwtAuth.JwtAuthenticationWithSpringBoot.Repository.EmployeeRepo;
import com.JwtAuth.JwtAuthenticationWithSpringBoot.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.JwtAuth.JwtAuthenticationWithSpringBoot.Service.EmployeeService;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/employee")
public class Controller {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private EmployeeRepo employeerepo;

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping("/CreateEmployee/")
    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody EmployeeModel employeeModel) {
        try {
            employeeService.saved(employeeModel);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("status", true);
            successResponse.put("message", "New employee is created successfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/registerEmployee/{employeeId}")
    public ResponseEntity<String> registerEmployee(@PathVariable Integer employeeId){
        try{
            Optional<EmployeeModel> employee = employeeService.findById(employeeId);
            employeeService.registerEmployee(employeeId);
            return ResponseEntity.status(HttpStatus.OK).body("Employee successfully registered");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String employeeCode = loginRequest.getEmployeeCode();
        String password = loginRequest.getPassword();

        LoginResponse loginResponse = authenticationService.authenticate(employeeCode, password);

        if (loginResponse != null) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid credentials",null,null));
        }
    }

    @GetMapping("/test/")
    public ResponseEntity<?> test(Principal principal) {
        String employeeCodejwt = principal.getName();
        System.out.println(employeeCodejwt);
        EmployeeModel employee = employeerepo.findByEmployeeCode(employeeCodejwt);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, Object>> handleException(Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", false);
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
}
