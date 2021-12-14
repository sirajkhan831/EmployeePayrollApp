package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = {"/payroll"})
public class EmployeePayrollController {

    @Autowired
    EmployeePayrollService service;

    @GetMapping(value = {"/hello"})
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PostMapping(value = {"/add-employee"})
    public ResponseEntity<String> addEmp(
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        return new ResponseEntity<>(service.addEmployee(employeeDto), HttpStatus.OK);
    }

    @GetMapping(value = {"get-all-employee"})
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return new ResponseEntity<>(service.getEmployees(), HttpStatus.OK);
    }

    @PutMapping("/update-employee/{id}")
    public ResponseEntity<String> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        service.updateEmployee(employeeDto, id);
        return new ResponseEntity<>(service.updateEmployee(employeeDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id
    ) {
        return new ResponseEntity<>(service.deleteEmployee(id), HttpStatus.OK);
    }
}