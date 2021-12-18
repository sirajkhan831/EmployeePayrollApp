package com.bridgelabz.employeepayrollapp.jwt.controller;

import com.bridgelabz.employeepayrollapp.exception.ResourceException;
import com.bridgelabz.employeepayrollapp.jwt.model.JwtRequest;
import com.bridgelabz.employeepayrollapp.jwt.helper.JwtUtil;
import com.bridgelabz.employeepayrollapp.jwt.service.CustomUserDetailsService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

/**
 * Purpose : JWT controller to generate and manage tokens.
 *
 * @author Siraj
 * @version 1.0
 * @since 17/12/2021
 */
@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Purpose : POST Request mapping to generate a new token.
     *
     * @param jwtRequest : Request object containing id & pass.
     * @return : Returns a response entity containing the token.
     * @throws Exception : Throws exception if user sends wrong credentials.
     */
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        if (!Objects.equals(jwtRequest.getUsername(), "Admin")) {
            jwtRequest.setPassword(BCrypt.hashpw(jwtRequest.getPassword(), "$2a$10$g6MB/m7t4QcprJkjuE/s9u"));
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        return ResponseEntity.of(Optional.of(token));
    }
}