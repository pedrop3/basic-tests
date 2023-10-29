package com.learn.basicTests.service.impl;

import org.springframework.stereotype.Service;

import com.learn.basicTests.customException.InvalidadeLengthCpfException;
import com.learn.basicTests.customException.PersonNotFoundException;
import com.learn.basicTests.model.Person;
import com.learn.basicTests.repository.PersonRepository;
import com.learn.basicTests.service.PersonService;
import com.learn.basicTests.util.CpfValidator;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        this.validationCpf(person.getCpf());
        return this.personRepository.save(person);
    }

    public boolean validationCpf(String cpf) {

        if (cpf.length() != 11) {
            throw new InvalidadeLengthCpfException("CPF with invalide size", "invalide-length-cpf");
        }

        return CpfValidator.isValidCpf(cpf);
    }

    public Person findById(Long personId) {
        return this.personRepository.findById(personId)
                .orElseThrow(()-> new PersonNotFoundException("Person not found","person-not-found"));
    }

}
