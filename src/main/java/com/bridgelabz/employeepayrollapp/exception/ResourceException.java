package com.bridgelabz.employeepayrollapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Purpose : Custom exception when resource is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceException extends RuntimeException {
    public ResourceException() {
        super("No employee with the given ID exists");
    }
}