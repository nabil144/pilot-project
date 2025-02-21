package com.example.administration.controller;

import com.example.administration.dto.CustomResponseDTO;
import com.example.administration.entity.Person;
import com.example.administration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService thePersonService) {
        personService = thePersonService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Person> insertPerson(@RequestBody Person newPerson) {
        return ResponseEntity.ok(personService.savePerson(newPerson));
    }

    @GetMapping("/person/{name}")
    public ResponseEntity<CustomResponseDTO<Person>> findPerson(@PathVariable String name) {
        Optional<Person> person = personService.findPersonByName(name);

        ResponseEntity<CustomResponseDTO<Person>> response;
        CustomResponseDTO<Person> customResponseDTO = new CustomResponseDTO();

        if(person.isPresent()){
            customResponseDTO.setCode(0);
            customResponseDTO.setMessage("person is a member");
            customResponseDTO.setData(person.get());
            return ResponseEntity.ok(customResponseDTO);
        }
        customResponseDTO.setCode(1);
        customResponseDTO.setMessage("person is not a member");
        customResponseDTO.setData(null);
        return ResponseEntity.ok(customResponseDTO);
    }

}
