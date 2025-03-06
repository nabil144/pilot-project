package com.example.adminms.service;

import com.example.adminms.dto.CustomResponseDTO;
import com.example.adminms.entity.Person;

public interface PersonService {

    Person savePerson(Person newPerson);

    Person updatePerson(Person person, int id);

    void deletePersonById(int id);

    CustomResponseDTO<Person> findPersonByName(String name);

}
