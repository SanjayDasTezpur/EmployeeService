package com.general.society.employee.service.exceptions;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadParamsException extends RuntimeException {

    public BadParamsException(String message) {
        super(StringUtils.isEmpty(message) ? "Missing required fields in the query" : message);
    }

}
