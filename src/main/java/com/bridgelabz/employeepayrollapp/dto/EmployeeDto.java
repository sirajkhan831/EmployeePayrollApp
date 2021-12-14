package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EmployeeDto {

    private int empId;

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]{3,28}$", message = "The name can not contain any numbers or symbols.")
    private String empName;

    @NotNull
    @Pattern(regexp = "^[A-Z]{1}$", message = "The gender should be one character in length")
    private String empGender;

    @NotNull
    @Pattern(regexp = "^[A-Z]{2,5}$", message = "Please use only department initials")
    private String empDepartment;

    @NotNull
    @Pattern(regexp = "^[0-9]{4,7}$", message = "Salary can not contain any alphabets and can not be larger than 7 digits")
    private String empSalary;

    @NotNull
    @Pattern(regexp = "^[0-3][0-9]/[0-9]{1,2}/[2][0-9]{3}$", message = "Please provide valid date and in the format : dd/mm/YYYY")
    private String empStartDate;

    @NotNull
    private String empNotes;

    @NotNull
    @Pattern(regexp = "^[/][A-Za-z]{1,18}[/][A-Za-z0-9]{1,30}.jpg$", message = "Invalid file path")
    private String empImagePath;

}
