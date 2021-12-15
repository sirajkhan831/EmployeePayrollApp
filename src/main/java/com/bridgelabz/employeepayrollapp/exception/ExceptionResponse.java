package com.bridgelabz.employeepayrollapp.exception;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Purpose: Exception object to carry data from exception.
 *
 * @author Siraj
 * @version 1.0
 * @since 12-12-2021
 **/
@Data
public class ExceptionResponse {

    private Date timestamp;
    private int status;
    private List<String> error;
}
