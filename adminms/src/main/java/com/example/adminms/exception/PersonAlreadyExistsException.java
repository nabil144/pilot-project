package com.example.adminms.exception;

public class PersonAlreadyExistsException extends RuntimeException {

    private String personName;

    public PersonAlreadyExistsException(){}

    public PersonAlreadyExistsException(String personName) {
        super("person " + personName + " already exists as a member");
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
