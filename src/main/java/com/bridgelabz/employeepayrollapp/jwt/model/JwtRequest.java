package com.bridgelabz.employeepayrollapp.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Purpose : JWT Request object to hold user data.
 * @author Siraj
 * @version 1.0
 * @since 17/12/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    String password;
    String username;
}
