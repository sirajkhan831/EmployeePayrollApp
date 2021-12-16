package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTestCase {

    @InjectMocks
    private EmployeePayrollController controller;

    @Mock
    private EmployeePayrollService service;

    private EmployeeDto employeeDto;
    private EmployeeDto employeeDto2;

    @BeforeEach
    void setUp() {
        employeeDto = new EmployeeDto();
        employeeDto.setEmpName("Siraj Khan");
        employeeDto.setEmpGender("M");
        employeeDto.setEmpDepartment("IT");
        employeeDto.setEmpSalary("30000");
        employeeDto.setEmpStartDate("29/06/2021");
        employeeDto.setEmpNotes("Note");
        employeeDto.setEmpImagePath("/pictures/1.jpg");
        employeeDto2 = new EmployeeDto();
        employeeDto2.setEmpName("Rahul Singh");
        employeeDto2.setEmpGender("M");
        employeeDto2.setEmpDepartment("IT");
        employeeDto2.setEmpSalary("25000");
        employeeDto2.setEmpStartDate("02/07/2021");
        employeeDto2.setEmpNotes("Note");
        employeeDto2.setEmpImagePath("/pictures/2.jpg");
    }

    @Test
    void givenTwoEmployeeDto_whenGetCalled_shouldReturnListOfEmployee() {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        service.addEmployee(employeeDto);
        service.addEmployee(employeeDto2);
        employeeDtoList.add(employeeDto);
        employeeDtoList.add(employeeDto2);
        when(service.getEmployees()).thenReturn(employeeDtoList);
        List<EmployeeDto> actualResponse = controller.getAllEmployees().getBody();
        for (int i = 0; i < Objects.requireNonNull(actualResponse).size(); i++) {
            Assertions.assertEquals(employeeDtoList.get(i).getEmpName(), actualResponse.get(i).getEmpName());
            Assertions.assertEquals(employeeDtoList.get(i).getEmpGender(), actualResponse.get(i).getEmpGender());
            Assertions.assertEquals(employeeDtoList.get(i).getEmpSalary(), actualResponse.get(i).getEmpSalary());
        }
    }

    @Test
    void givenEmployeeDto_whenAdded_shouldReturnResponseEntity() {
        String successMessage = "Employee Added Successfully";
        when(service.addEmployee(employeeDto)).thenReturn(successMessage);
        ResponseEntity<String> responseEntity = controller.addEmp(employeeDto);
        Assertions.assertEquals(successMessage, responseEntity.getBody());
        Assertions.assertEquals(successMessage, responseEntity.getBody());
    }

    @Test
    void givenEmployeeDto_whenUpdatedEmployee_shouldReturnResponseEntity() {
        String successMessage = "Employee Updated Successfully";
        int id = 1;
        when(service.updateEmployee(employeeDto, id)).thenReturn(successMessage);
        ResponseEntity<String> responseEntity = controller.updateEmployee(id, employeeDto);
        Assertions.assertEquals(successMessage, responseEntity.getBody());
    }

    @Test
    void givenEmployeeId_whenDeleted_shouldReturnResponseEntity() {
        String successMessage = "Employee Deleted Successfully";
        int id = 1;
        when(service.deleteEmployee(id)).thenReturn(successMessage);
        ResponseEntity<String> responseEntity = controller.deleteEmployee(id);
        Assertions.assertEquals(successMessage, responseEntity.getBody());
    }
}
