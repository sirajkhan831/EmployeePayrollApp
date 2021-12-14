package com.bridgelabz.employeepayrollapp;

import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootApplication
public class EmployeePayrollAppApplication {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeePayrollAppApplication.class, args);
    }
}