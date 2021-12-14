package com.bridgelabz.employeepayrollapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Purpose: Payroll system application created to manage employee payroll.
 *
 * @author Siraj
 * @version 1.0
 * @since 10-12-2021
 **/
@SpringBootApplication
public class EmployeePayrollAppApplication {

    /**
     * Purpose : Creating instance of Model mapper to map objects and entities.
     *
     * @return : Returns a new model mapper
     */
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeePayrollAppApplication.class, args);
    }
}