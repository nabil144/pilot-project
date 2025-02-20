package com.example.administration.service;

import com.example.administration.entity.Person;

import java.util.Optional;

public interface PersonService {

    Person savePerson(Person newPerson);

    Person updatePerson(Person person, int id);

    void deletePersonById(int id);

    Optional<Person> findPersonByName(String name);

}
