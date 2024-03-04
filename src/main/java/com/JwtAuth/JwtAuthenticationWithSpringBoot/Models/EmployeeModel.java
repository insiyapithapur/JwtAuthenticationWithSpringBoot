package com.JwtAuth.JwtAuthenticationWithSpringBoot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeModel {
    @Id
    @GeneratedValue
    private Integer id;
    private String employee_name;
    private String employeeCode;
    private String employee_dept;
    private String employee_pass;
    private String employee_role;
    private boolean status;
    public EmployeeModel(String employeeCode, String employee_dept, String employee_name,String employee_role,boolean status) {
        this.employeeCode = employeeCode;
        this.employee_dept = employee_dept;
        this.employee_name = employee_name;
        this.employee_role =employee_role;
        this.status = status;
    }
}
