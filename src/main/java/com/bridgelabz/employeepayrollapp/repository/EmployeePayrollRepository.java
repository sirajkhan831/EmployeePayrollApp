package com.bridgelabz.employeepayrollapp.repository;

import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *  Purpose: Payroll Repository for handling database repository.
 *  @version 1.0
 *  @since 11-12-2021
 **/
@Repository
public interface EmployeePayrollRepository extends JpaRepository<EmployeeEntity, Integer> {

}
