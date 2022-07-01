package com.sam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Person Not Found")
public class PersonNotFoundException extends Exception {
    private static final long serialVersionsUID = 1L;

    public PersonNotFoundException(){
        super("Person Not Found");
    }
}
