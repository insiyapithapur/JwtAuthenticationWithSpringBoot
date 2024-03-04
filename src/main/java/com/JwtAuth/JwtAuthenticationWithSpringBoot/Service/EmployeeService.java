package com.JwtAuth.JwtAuthenticationWithSpringBoot.Service;

import com.JwtAuth.JwtAuthenticationWithSpringBoot.Models.EmployeeModel;
import com.JwtAuth.JwtAuthenticationWithSpringBoot.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repo;

    public void saved(EmployeeModel employeemodel) {
        repo.save(employeemodel);
    }

    public Optional<EmployeeModel> findById(Integer id){
        return repo.findById(id);
    }

    public void registerEmployee(Integer employeeId){
        //Todo: Add Exception handling
        EmployeeModel employee = repo.findById(employeeId).orElse(null);
        if (employee != null && !employee.isStatus()) {
            String generatePassword = generatePassword();
            employee.setEmployee_pass(generatePassword);
            employee.setStatus(true);
            repo.save(employee);
        }
    }
    private String generatePassword() {
        // Implement your password generation logic here
        return "GeneratedPassword123"; // Dummy password for demonstration
    }

    public EmployeeModel getEmployeeDetailsByEmployeeCode(String employee_code, String password) {
        EmployeeModel employeeDetails = repo.findByEmployeeCode(employee_code);
        if(employeeDetails != null && password.equals(employeeDetails.getEmployee_pass())) {
            return employeeDetails;
        }
        return null;
    }
}
