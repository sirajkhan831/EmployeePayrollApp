package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;

import java.util.List;

public interface EmployeePayrollServiceInterface {

    List<EmployeeDto> getEmployees();

    String deleteEmployee(int id);

    String updateEmployee(EmployeeDto employeeDto, int id);

    String addEmployee(EmployeeDto employeeDto);
}
