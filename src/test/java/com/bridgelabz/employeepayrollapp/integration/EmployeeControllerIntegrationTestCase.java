package com.bridgelabz.employeepayrollapp.integration;

import com.bridgelabz.employeepayrollapp.controller.EmployeePayrollController;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeePayrollController.class)
public class EmployeeControllerIntegrationTestCase {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeePayrollService service;

    EmployeeDto employeeDto;
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonRequest;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        employeeDto = new EmployeeDto();
        employeeDto.setEmpName("Siraj Khan");
        employeeDto.setEmpGender("M");
        employeeDto.setEmpDepartment(List.of("IT"));
        employeeDto.setEmpSalary("30000");
        employeeDto.setEmpStartDate("29/06/2021");
        employeeDto.setEmpNotes("Note");
        employeeDto.setEmpImagePath("/pictures/1.jpg");
        jsonRequest = objectMapper.writeValueAsString(employeeDto);
    }

    @Test
    void whenSentGetRequest_shouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/payroll/api/get-all-employee"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenSentPostRequest_ShouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/payroll/api/add-employee")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenSentDeleteRequest_ShouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/payroll/api/delete-employee/11"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenSentPutRequest_ShouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/payroll/api/update-employee/1")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenSentInvalidPostRequest_ShouldReturnStatusBadRequest() throws Exception {
        employeeDto.setEmpSalary("10");
        jsonRequest = objectMapper.writeValueAsString(employeeDto);
        mvc.perform(MockMvcRequestBuilders
                        .post("/payroll/api/add-employee")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
