package com.btest.springsecurityjwt.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class CustomException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public CustomException(String message){
        super(message);
    }

    public CustomException(){}
}
