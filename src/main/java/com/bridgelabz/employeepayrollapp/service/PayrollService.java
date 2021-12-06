package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.repository.PayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    private static final String EMP_ADDED_SUCCESSFULLY = "Employee Added Successfully";
    private static final String EMP_DELETED_SUCCESSFULLY = "Employee Deleted Successfully";
    private static final String EMP_UPDATED_SUCCESSFULLY = "Employee Updated Successfully";

    @Autowired
    PayrollRepository payrollRepository;

    @Autowired
    ModelMapper mapper;

    public String addEmp(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = mapper.map(employeeDto, EmployeeEntity.class);
        payrollRepository.save(employeeEntity);
        return EMP_ADDED_SUCCESSFULLY;
    }

    public List<EmployeeDto> getEmployees() {
        return payrollRepository.findAll().stream().map(employeeEntity -> mapper.map(employeeEntity, EmployeeDto.class)).collect(Collectors.toList());
    }

    public String deleteEmployee(int id) {
        payrollRepository.deleteById(id);
        return EMP_DELETED_SUCCESSFULLY;
    }
}
