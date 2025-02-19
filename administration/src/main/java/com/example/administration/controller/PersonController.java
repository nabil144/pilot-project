package com.example.administration.controller;

import com.example.administration.entity.Person;
import com.example.administration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService thePersonService){
        personService = thePersonService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Person> insertPerson(@RequestBody Person newPerson){
        return ResponseEntity.ok(personService.savePerson(newPerson));
    }

    @GetMapping("/person/{name}")
    public ResponseEntity<Person> findPerson(@PathVariable String name){
        Person person = personService.findPersonByName(name);
        if(person==null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(person,HttpStatus.OK);
    }

}
