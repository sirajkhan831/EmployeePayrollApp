package com.bridgelabz.employeepayrollapp.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Table(name = "payroll")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]{3,28}$", message = "The name can not contain any numbers or symbols.")
    @Column(name = "emp_name")
    private String empName;

    @NotNull
    @Pattern(regexp = "^[0-9]{4,6}$", message = "CVV must have exactly 3 digits.")
    @Column(name = "emp_salary")
    private String empSalary;
}
