package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTestCase {

    EmployeeDto employeeDto = new EmployeeDto();

    @InjectMocks
    private EmployeePayrollController employeePayrollController;
    @Mock
    private EmployeePayrollService employeePayrollService;

    @BeforeEach
    void setUp() {
        employeeDto.setEid(1);
        employeeDto.setEmpGender("M");
        employeeDto.setEmpDepartment("IT");
        employeeDto.setEmpName("Siraj Khan");
        employeeDto.setEmpNotes("None");
        employeeDto.setEmpSalary("30000");
        employeeDto.setEmpStartDate("12/12/21");
    }

    @Test
    void whenGetAllEmpCalled_shouldReturnListOfEmpDto() {
        List<EmployeeDto> employeeDtoList;
        EmployeeDto dto = new EmployeeDto();
        dto.setEid(2);
        dto.setEmpGender("M");
        dto.setEmpDepartment("IT");
        dto.setEmpName("Hello World");
        dto.setEmpNotes("None");
        dto.setEmpSalary("20000");
        dto.setEmpStartDate("11/12/21");
        employeeDtoList = List.of(employeeDto, dto);
        when(employeePayrollService.getEmployees()).thenReturn(employeeDtoList);
        List<EmployeeDto> actualResponse = employeePayrollController.getAllEmployees();
        for (int i = 0; i < actualResponse.size(); i++) {
            assertEquals(employeeDtoList.get(i).getEid(), actualResponse.get(i).getEid());
            assertEquals(employeeDtoList.get(i).getEmpName(), actualResponse.get(i).getEmpName());
            assertEquals(employeeDtoList.get(i).getEmpGender(), actualResponse.get(i).getEmpGender());
            assertEquals(employeeDtoList.get(i).getEmpDepartment(), actualResponse.get(i).getEmpDepartment());
            assertEquals(employeeDtoList.get(i).getEmpSalary(), actualResponse.get(i).getEmpSalary());
        }
    }
}
