package com.example.adminms.controller;

import com.example.adminms.dto.CustomResponseDTO;
import com.example.adminms.entity.Person;
import com.example.adminms.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/insert")
    public ResponseEntity<Person> insertPerson(@RequestBody Person newPerson) {
        return ResponseEntity.ok(personService.savePerson(newPerson));
    }

    @GetMapping("/person/{name}")
    public ResponseEntity<CustomResponseDTO<Person>> findPerson(@PathVariable String name) {
        CustomResponseDTO<Person> customResponseDTO = personService.findPersonByName(name);
        return ResponseEntity.ok(customResponseDTO);
    }

}
