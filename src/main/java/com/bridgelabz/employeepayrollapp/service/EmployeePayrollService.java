package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.exception.ResourceException;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * Purpose : Fetches all the employee in the repository.
     *
     * @return : Returns a list of employee.
     */
    public List<EmployeeDto> getEmployees() {
        return employeePayrollRepository.findAll()
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Purpose : Deletes employee from the repository.
     *
     * @return : Returns a String is the object is deleted successfully.
     */
    public String deleteEmployee(int id) {
        if (employeePayrollRepository.findById(id).equals(Optional.empty())) {
            throw new ResourceException();
        }
        employeePayrollRepository.deleteById(id);
        return EMP_DELETED_SUCCESSFULLY;
    }

    /**
     * Purpose : Adds new employee in the repository.
     *
     * @return : Returns a String if the object is added successfully.
     */
    public String addEmployee(EmployeeDto employeeDto) {
        if (!employeePayrollRepository.findById(employeeDto.getEid()).equals(Optional.empty())) {
            employeeDto.setEid(eidGenerator());
        }
        EmployeeEntity employeeEntity = mapper.map(employeeDto, EmployeeEntity.class);
        employeePayrollRepository.save(employeeEntity);
        return EMP_ADDED_SUCCESSFULLY;
    }

    /**
     * Purpose : Updates new employee in the repository.
     *
     * @return : Returns a String if the object is updated successfully.
     */
    public String updateEmployee(EmployeeDto employeeDto, int id) {
        if (employeePayrollRepository.findById(id).equals(Optional.empty())) {
            throw new ResourceException();
        }
        EmployeeEntity employeeEntity = mapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setEid(id);
        employeePayrollRepository.save(employeeEntity);
        return EMP_UPDATED_SUCCESSFULLY;
    }

    /**
     * Purpose : EID generator method to generate integer id for entities.
     *
     * @return Returns nearest available ID.
     */
    private int eidGenerator() {
        int eid = 0;
        for (EmployeeEntity entity : employeePayrollRepository.findAll()) {
            eid++;
            if (eid != entity.getEid()) {
                return eid;
            }
        }
        return getEmployees().size() + 1;
    }
}