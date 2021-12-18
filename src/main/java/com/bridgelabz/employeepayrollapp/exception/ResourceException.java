package com.bridgelabz.employeepayrollapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Purpose : Custom exception when resource is not found.
 * @author Siraj
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceException extends RuntimeException {
    public ResourceException() {
        super("No employee with the given ID exists");
    }

    public ResourceException(String message) {
        super(message);
    }
}