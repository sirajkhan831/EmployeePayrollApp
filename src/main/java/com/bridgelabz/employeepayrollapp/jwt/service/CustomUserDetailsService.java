package com.bridgelabz.employeepayrollapp.jwt.service;

import com.bridgelabz.employeepayrollapp.entity.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Purpose : User service to manage users.
 *
 * @author Siraj
 * @version 1.0
 * @since 17/12/2021
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeePayrollRepository repository;

    /**
     * Purpose : Method used to verify the DB with the given username
     *
     * @param username : Username of the user
     * @return : Returns a user detail
     * @throws UsernameNotFoundException : Exception thrown if user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String name = "Admin";
        String pass = "admin";
        for (EmployeeEntity entity : repository.findAll()) {
            if (username.equals(entity.getEmpName())) {
                name = entity.getEmpName();
                pass = entity.getEmpPassword();
                return new User(name, pass, new ArrayList<>());
            }
        }
        return new User(name, pass, new ArrayList<>());
    }
}