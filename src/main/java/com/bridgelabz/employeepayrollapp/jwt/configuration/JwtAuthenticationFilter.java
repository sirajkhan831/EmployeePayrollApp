package com.bridgelabz.employeepayrollapp.jwt.configuration;

import com.bridgelabz.employeepayrollapp.jwt.helper.JwtUtil;
import com.bridgelabz.employeepayrollapp.jwt.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Purpose : JWT Authentication filter to verify all links for tokens.
 *
 * @author Siraj
 * @version 1.0
 * @since 17/12/2021
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Purpose : filter method to manage url authentication
     *
     * @param request     : request passed by user
     * @param response    : response to send to user
     * @param filterChain : responsible for filtering
     *                    unauthorized requests
     * @throws ServletException : throws exception if token is null
     * @throws IOException      : IO exception thrown if received improper data
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("token") != null) {
            String username = jwtUtil.extractUsername(request.getHeader("token"));
            assert username != null;
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}
