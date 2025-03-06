package com.example.adminms.service;

import com.example.adminms.dto.CustomResponseDTO;
import com.example.adminms.entity.Person;
import com.example.adminms.enums.ResponseCode;
import com.example.adminms.exception.PersonAlreadyExistsException;
import com.example.adminms.exception.PersonNotMemberException;
import com.example.adminms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository thePersonRepository){
        personRepository = thePersonRepository;
    }

    @Override
    public Person savePerson(Person newPerson) {
        try{
            CustomResponseDTO customResponseDTO = findPersonByName(newPerson.getName());
            if(customResponseDTO.getCode()== ResponseCode.OK){
                throw new PersonAlreadyExistsException(newPerson.getName());
            }else{
                return personRepository.save(newPerson);
            }
        }catch(PersonNotMemberException e){
            return personRepository.save(newPerson);
        }catch(Exception e){
            throw e;
        }
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
    public CustomResponseDTO<Person> findPersonByName(String name) {
        List<Person> people = personRepository.findByName(name);
        if(people.isEmpty()){
            throw new PersonNotMemberException(name);
        }
        CustomResponseDTO<Person> customResponseDTO = new CustomResponseDTO<>(ResponseCode.OK,"person is a member", people.get(0));
        return customResponseDTO;
    }
}
