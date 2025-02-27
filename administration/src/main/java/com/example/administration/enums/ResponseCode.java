package com.example.administration.enums;

import org.springframework.http.HttpStatus;

public enum ResponseCode {
    OK(0, "operation successful",HttpStatus.OK),

    PERSON_NOT_MEMBER(1, "person not member", HttpStatus.FORBIDDEN),
    UNAUTHORIZED(2,"unauthorized operation", HttpStatus.UNAUTHORIZED),
    PERSON_ALREADY_EXISTS(3,"person already exists", HttpStatus.CONFLICT),
    INTERNAL_SERVER_ERROR(5,"internal server error", HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;


    ResponseCode(int code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
