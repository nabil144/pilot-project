package com.example.administration.service;


import com.example.administration.entity.Person;
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
    public Person updatePerson(Person person, int id) {
        return null;
    }

    @Override
    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> findPersonByName(String name) {
        List<Person> people = personRepository.findByName(name);
        if(people.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(people.get(0));
    }
}
