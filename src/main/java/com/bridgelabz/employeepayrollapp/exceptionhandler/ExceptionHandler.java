package com.bridgelabz.employeepayrollapp.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

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
        ErrorObject object = new ErrorObject();
        object.setTimestamp(new Date());
        object.setStatus(status.value());
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error -> {
            errors.add(error.getDefaultMessage());
        }));
        object.setError(errors);
        return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
    }
}