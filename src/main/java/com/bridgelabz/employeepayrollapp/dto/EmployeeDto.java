package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EmployeeDto {

    private int eid;

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]{3,28}$", message = "The name can not contain any numbers or symbols.")
    private String empName;

    @NotNull
    @Pattern(regexp = "[A-Z]", message = "The gender should be one character in length")
    private String empGender;

    @NotNull
    @Pattern(regexp = "[A-Z]{3}")
    private String empDepartment;

    @NotNull
    @Pattern(regexp = "[0-9]{4,7}")
    @Pattern(regexp = "^[0-9]{4,7}$", message = "Salary can not contain any alphabets and can not be larger than 7 digits")
    private String empSalary;

    @NotNull
    @Pattern(regexp = "[0-9/-]{9,11}")
    private String empStartDate;

    @NotNull
    private String empNotes;

    @NotNull
    private String empImagePath;

}
