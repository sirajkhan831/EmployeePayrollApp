package com.bridgelabz.employeepayrollapp.component;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeePayrollBuilder {

    @Autowired
    ModelMapper mapper;

    public EmployeeEntity builder(EmployeeDto dto, EmployeeEntity entity) {
        return mapper.map(dto, EmployeeEntity.class);
    }
}
