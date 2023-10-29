package com.learn.basicTests.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learn.basicTests.dto.PersonDTO;
import com.learn.basicTests.model.Person;
import com.learn.basicTests.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PersonDTO personDTO) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            Person person = modelMapper.map(personDTO, Person.class);

            person = personService.save(person);

            return ResponseEntity
                    .created(
                            ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{personId}")
                                    .buildAndExpand(person.getId())
                                    .toUri())
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}