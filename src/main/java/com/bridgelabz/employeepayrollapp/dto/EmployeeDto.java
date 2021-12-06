package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EmployeeDto {

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]{3,28}$", message = "The name can not contain any numbers or symbols.")
    private String empName;

    @NotNull
    @Pattern(regexp = "^[0-9]{4,6}$", message = "CVV must have exactly 3 digits.")
    private String empSalary;
}
