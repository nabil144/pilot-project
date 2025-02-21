package com.example.administration.exceptions;

public class PersonNotMemberException extends RuntimeException{

    private String message;

    public PersonNotMemberException(){}

    public PersonNotMemberException(String message) {
        super(message);
        this.message = message;
    }

    public PersonNotMemberException(String message, Throwable cause){
        super(message,cause);
    }
}
