package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.exceptionhandler.ResourceException;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTestCase {

    @InjectMocks
    private EmployeePayrollService service;

    @Mock
    private EmployeePayrollRepository repository;

    @Mock
    private ModelMapper modelMapper;

    EmployeeDto employeeDto;
    EmployeeDto employeeDto2;

    EmployeeEntity employeeEntity;
    EmployeeEntity employeeEntity2;

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
        employeeEntity = new EmployeeEntity();
        employeeEntity.setEid(1);
        employeeEntity.setEmpName("Siraj Khan");
        employeeEntity.setEmpGender("M");
        employeeEntity.setEmpDepartment("IT");
        employeeEntity.setEmpSalary("30000");
        employeeEntity.setEmpStartDate("29/06/2021");
        employeeEntity.setEmpNotes("Note");
        employeeEntity.setEmpImagePath("/pictures/1.jpg");
        employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setEid(2);
        employeeEntity2.setEmpName("Rahul Singh");
        employeeEntity2.setEmpGender("M");
        employeeEntity2.setEmpDepartment("IT");
        employeeEntity2.setEmpSalary("25000");
        employeeEntity2.setEmpStartDate("02/07/2021");
        employeeEntity2.setEmpNotes("Note");
        employeeEntity2.setEmpImagePath("/pictures/2.jpg");
    }

    @Test
    void givenAListOfEmployees_whenGetEmployeeCalled_shouldReturnListOfEmployee() {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(employeeDto);
        employeeDtoList.add(employeeDto2);

        List<EmployeeEntity> entityList = new ArrayList<>();
        entityList.add(employeeEntity);
        entityList.add(employeeEntity2);

        lenient().when(repository.findAll()).thenReturn(entityList);
        when(modelMapper.map(entityList.get(0), EmployeeDto.class)).thenReturn(employeeDto);
        when(modelMapper.map(entityList.get(1), EmployeeDto.class)).thenReturn(employeeDto2);
        List<EmployeeDto> actualListOfEmployee = service.getEmployees();
        Assertions.assertEquals(2, actualListOfEmployee.size());
        Assertions.assertEquals(employeeDtoList, actualListOfEmployee);
    }

    @Test
    void givenEmployeeDto_whenCalledAddEmployee_shouldReturnSuccessMessage() {
        String successMessage = "Employee Added Successfully";
        when(modelMapper.map(employeeDto, EmployeeEntity.class)).thenReturn(employeeEntity);
        String actualMessage = service.addEmployee(employeeDto);
        Assertions.assertEquals(successMessage, actualMessage);
        verify(repository, times(1)).save(employeeEntity);
    }

    @Test
    void givenEmployeeIdPayrollDto_whenCalledDeleteEmployee_shouldReturnSuccessMessage() {
        String successMessage = "Employee Deleted Successfully";
        lenient().when(repository.findById(1)).thenReturn(Optional.of(employeeEntity));
        String actualMessage = service.deleteEmployee(1);
        Assertions.assertEquals(successMessage, actualMessage);
    }

    @Test
    void givenAEmployeeDetails_whenUpdateEmployeeIsCalled_shouldThrowExceptionMessage() {
        int employeeId = 4;
        when(repository.findById(employeeId)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceException.class, () -> service.updateEmployee(employeeDto, employeeId));
    }
}
