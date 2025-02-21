package com.example.administration.exceptions;

public class PersonAlreadyExistsException extends RuntimeException {
    private String message;

    public PersonAlreadyExistsException(){}

    public PersonAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    public PersonAlreadyExistsException(String message, Throwable cause){
        super(message,cause);
    }
}
