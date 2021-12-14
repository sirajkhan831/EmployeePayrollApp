package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.exceptionhandler.ResourceException;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Purpose: Payroll Service for holding the business logic.
 *
 * @author Siraj
 * @version 1.0
 * @since 11-12-2021
 **/
@Service
public class EmployeePayrollService {

    private static final String EMP_ADDED_SUCCESSFULLY = "Employee Added Successfully";
    private static final String EMP_DELETED_SUCCESSFULLY = "Employee Deleted Successfully";
    private static final String EMP_UPDATED_SUCCESSFULLY = "Employee Updated Successfully";

    @Autowired
    EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    ModelMapper mapper;

    /**
     * Purpose : Adds new employee in the repository.
     *
     * @return : Returns a String if the object is added successfully.
     */
    public String addEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = mapper.map(employeeDto, EmployeeEntity.class);
        employeePayrollRepository.save(employeeEntity);
        return EMP_ADDED_SUCCESSFULLY;
    }

    /**
     * Purpose : Fetches all the employee in the repository.
     *
     * @return : Returns a list of employee.
     */
    public List<EmployeeDto> getEmployees() {
        List<EmployeeDto> employees = new ArrayList<>();
        for (EmployeeEntity entity : employeePayrollRepository.findAll()) {
            EmployeeDto dto = mapper.map(entity, EmployeeDto.class);
            dto.setEmpId(entity.getEid());
            employees.add(dto);
        }
        return employees;
    }

    /**
     * Purpose : Deletes employee from the repository.
     *
     * @return : Returns a String is the object is deleted successfully.
     */
    public String deleteEmployee(int id) {
        employeePayrollRepository.deleteById(id);
        return EMP_DELETED_SUCCESSFULLY;
    }

    /**
     * Purpose : Updates new employee in the repository.
     *
     * @return : Returns a String if the object is updated successfully.
     */
    public String updateEmployee(EmployeeDto employeeDto, int id) {
        if (employeePayrollRepository.findById(id).equals(Optional.empty())) {
            throw new ResourceException("No employee with the given ID found");
        }
        EmployeeEntity employeeEntity = mapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setEid(id);
        employeePayrollRepository.save(employeeEntity);
        return EMP_UPDATED_SUCCESSFULLY;
    }
}