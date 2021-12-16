package com.bridgelabz.employeepayrollapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

/**
 * Purpose: Payroll ControllerAdvice for handling exceptions across the whole application.
 *
 * @author Siraj
 * @version 1.0
 * @since 12-12-2021
 **/
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    ExceptionResponseObject object = new ExceptionResponseObject();

    /**
     * Purpose : Returns a response for MethodArgumentNotValidException.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        object.setTimestamp(new Date());
        object.setStatus(status.value());
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error -> {
            errors.add(error.getDefaultMessage());
        }));
        object.setError(errors);
        return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
    }

    /**
     * Purpose : Exception handler for resource not found exception.
     *
     * @param exception the exception
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceException exception) {
        object.setTimestamp(new Date());
        object.setStatus(400);
        object.setError(List.of(exception.getMessage()));
        return new ResponseEntity<>(object, HttpStatus.NOT_FOUND);
    }
}