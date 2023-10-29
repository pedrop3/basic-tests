package com.learn.basicTests.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.basicTests.dto.PersonDTO;
import com.learn.basicTests.model.Person;
import com.learn.basicTests.model.Person.TypePersonEnum;
import com.learn.basicTests.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PersonControllerTest {

    private final String BASE_URL = "/persons";

    @InjectMocks
    private PersonController personController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @Test
    public void shoudlCreatedPerson() throws Exception {

        var personMock = buildPerson();
        var personMockDTO = buildPersonDTO();

        when(personService.save(any(Person.class))).thenReturn(personMock);

        mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMockDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }



    public static Person buildPerson() {
        return Person.builder()
                .id(1L)
                .name("Pedro")
                .cpf("08790298624")
                .typePersonEnum(TypePersonEnum.SENDER)
                .build();
    }

    public static PersonDTO buildPersonDTO() {
        return PersonDTO.builder()
                .name("Pedro")
                .cpf("08790298624")
                .typePersonEnum(TypePersonEnum.SENDER)
                .build();
    }

}