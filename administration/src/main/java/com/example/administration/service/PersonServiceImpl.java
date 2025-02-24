package com.example.administration.service;


import com.example.administration.entity.Person;
import com.example.administration.exceptions.PersonNotMemberException;
import com.example.administration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository thePersonRepository){
        personRepository = thePersonRepository;
    }

    @Override
    public Person savePerson(Person newPerson) {
        return personRepository.save(newPerson);
    }

    @Override
    public Person updatePerson(Person person, int personId) {
        Person dbPerson = personRepository.findById(personId).get();
        if(person.getName()!=null && !person.getName().isEmpty()){
            dbPerson.setName(person.getName());
        }
        if(person.getMembership()!=null && !person.getMembership().isEmpty()){
            dbPerson.setMembership(person.getMembership());
        }
        return personRepository.save(dbPerson);    }

    @Override
    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> findPersonByName(String name) {
        List<Person> people = personRepository.findByName(name);
        if(people.isEmpty()){
            throw new PersonNotMemberException(name);
        }
        return Optional.of(people.get(0));
    }
}
