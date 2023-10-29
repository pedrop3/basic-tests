package com.learn.basicTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.learn.basicTests.customException.InvalidadeLengthCpfException;
import com.learn.basicTests.customException.PersonNotFoundException;
import com.learn.basicTests.model.Person;
import com.learn.basicTests.model.Person.TypePersonEnum;
import com.learn.basicTests.repository.PersonRepository;
import com.learn.basicTests.service.impl.PersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTests {

    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void shouldSavePerson() {

        Person person = buildPerson();

        when(personRepository.save(any())).thenReturn(person);
        var personSaved = personServiceImpl.save(person);

        assertEquals(person, personSaved);
    }

    @Test
    public void shouldValidedCPF() {

        var isvalidCPF = personServiceImpl.validationCpf("08790298624"); //TO DO melhorar validação
        assertEquals(true, isvalidCPF);
    }

    @Test
    public void shouldInValidedCPF() {

        var isvalidCPF = personServiceImpl.validationCpf("00000000000");
        assertEquals(false, isvalidCPF);
    }

    @Test
    public void shouldThrowsAnExceptionWhenLowCpf() {
        assertThrows(InvalidadeLengthCpfException.class,
                () -> personServiceImpl.validationCpf("123"));
    }

    @Test
    public void shouldFindPersonById() {
        var personMock = buildPerson();

        when(personRepository.findById(any())).thenReturn(Optional.of(personMock));
        var person = personServiceImpl.findById(personMock.getId());

        assertEquals(personMock, person);
    }

    @Test
    public void shouldNotFoundPersonById() {
        var personMock = buildPerson();

        when(personRepository.findById(2L)).thenReturn(Optional.of(personMock));

        assertThrows(PersonNotFoundException.class,
                () -> personServiceImpl.findById(3l));
        
        
    }

    @Test
    public void shouldvalidCpfWithChars() {
        var isvalidCPF = personServiceImpl.validationCpf("0879029862a");
        assertEquals(false, isvalidCPF);
    }

    public static Person buildPerson() {
        return Person.builder()
                .id(1L)
                .name("Pedro")
                .cpf("08790298624")
                .typePersonEnum(TypePersonEnum.SENDER)
                .build();
    }

}
