package com.example.administration.service;

import com.example.administration.entity.Person;

public interface PersonService {

    Person savePerson(Person newPerson);

    Person updatePerson(Person person, int id);

    void deletePersonById(int id);

    Person findPersonByName(String name);

}
