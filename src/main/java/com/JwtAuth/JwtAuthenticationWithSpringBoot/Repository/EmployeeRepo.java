package com.JwtAuth.JwtAuthenticationWithSpringBoot.Repository;

import com.JwtAuth.JwtAuthenticationWithSpringBoot.Models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeModel,Integer> {
    EmployeeModel findByEmployeeCode(String employeeCode);
}
