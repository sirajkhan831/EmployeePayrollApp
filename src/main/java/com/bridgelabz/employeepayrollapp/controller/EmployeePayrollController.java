package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = {"/payroll"})
public class EmployeePayrollController {

    @Autowired
    EmployeePayrollService service;

    @GetMapping(value = {"/hello"})
    public String sayHello() {
        return "Hello and welcome to payroll app";
    }

    @PostMapping(value = {"/add-Employee"})
    public String addEmp(
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        return service.addEmp(employeeDto);
    }

    @GetMapping(value = {"get-all-emp"})
    public List<EmployeeDto> getAllEmployees() {
        return service.getEmployees();
    }


    @PutMapping("/update/{id}")
    public String updateEmployee(
            @PathVariable int id,
            @RequestBody EmployeeDto employeeDto
    ) {
        return service.updateEmployee(id, employeeDto);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(
            @PathVariable int id
    ) {
        return service.deleteEmployee(id);
    }
}