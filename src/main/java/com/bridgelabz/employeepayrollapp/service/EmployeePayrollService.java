package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePayrollService {

    private static final String EMP_ADDED_SUCCESSFULLY = "Employee Added Successfully";
    private static final String EMP_DELETED_SUCCESSFULLY = "Employee Deleted Successfully";
    private static final String EMP_UPDATED_SUCCESSFULLY = "Employee Updated Successfully";

    @Autowired
    EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    ModelMapper mapper;

    public String addEmp(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = mapper.map(employeeDto, EmployeeEntity.class);
        employeePayrollRepository.save(employeeEntity);
        return EMP_ADDED_SUCCESSFULLY;
    }

    public List<EmployeeDto> getEmployees() {
        List<EmployeeDto> employees = new ArrayList<>();
        for (EmployeeEntity entity : employeePayrollRepository.findAll()) {
            EmployeeDto dto = mapper.map(entity, EmployeeDto.class);
            dto.setEid(entity.getEid());
            employees.add(dto);
        }
        return employees;
    }

    public String deleteEmployee(int id) {
        employeePayrollRepository.deleteById(id);
        return EMP_DELETED_SUCCESSFULLY;
    }

    public String updateEmployee(int id, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = mapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setEid(employeeDto.getEid());
        employeePayrollRepository.save(employeeEntity);
        return EMP_UPDATED_SUCCESSFULLY;
    }
}
