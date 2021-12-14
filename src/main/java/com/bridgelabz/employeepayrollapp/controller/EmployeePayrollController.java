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

/**
 * Purpose: Payroll RestController for RESTFul web services.
 *
 * @author Siraj
 * @version 1.0
 * @since 11-12-2021
 **/
@RestController
@RequestMapping(value = {"/payroll"})
public class EmployeePayrollController {

    /**
     * Purpose : Autowiring to create instance of payroll service.
     */
    @Autowired
    EmployeePayrollService service;

    /**
     * Purpose : Welcome message to greet the user.
     *
     * @return Returns a string with welcome message.
     */
    @GetMapping(value = {"/hello"})
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    /**
     * Purpose : Method to add new employee in the repository via POST.
     *
     * @return : Returns Response if the employee is successfully added.
     */
    @PostMapping(value = {"/add-employee"})
    public ResponseEntity<String> addEmp(
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        return new ResponseEntity<>(service.addEmployee(employeeDto), HttpStatus.OK);
    }

    /**
     * Purpose : Method to get all the employee dto in the repository via GET.
     *
     * @return : Returns Response of list if the employee is successfully fetched
     */
    @GetMapping(value = {"get-all-employee"})
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return new ResponseEntity<>(service.getEmployees(), HttpStatus.OK);
    }

    /**
     * Purpose : Method to update employee in the repository via PUT.
     *
     * @return : Returns Response if the employee is successfully updated
     */
    @PutMapping("/update-employee/{id}")
    public ResponseEntity<String> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        service.updateEmployee(employeeDto, id);
        return new ResponseEntity<>(service.updateEmployee(employeeDto, id), HttpStatus.OK);
    }

    /**
     * Purpose : Method to delete new employee in the repository via DELETE.
     *
     * @return : Returns Response if the employee is successfully deleted
     */
    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id
    ) {
        return new ResponseEntity<>(service.deleteEmployee(id), HttpStatus.OK);
    }
}