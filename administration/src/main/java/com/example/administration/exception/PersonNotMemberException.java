package com.example.administration.exception;

public class PersonNotMemberException extends RuntimeException{

    private String personName;

    public PersonNotMemberException(){}

    public PersonNotMemberException(String personName) {
        super("person with name " + personName + " is not a member");
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }
}
