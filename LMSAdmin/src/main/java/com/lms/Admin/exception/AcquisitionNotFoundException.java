package com.lms.Admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AcquisitionNotFoundException extends RuntimeException {
    public AcquisitionNotFoundException(String message) {
        super(message);
    }
}
