package com.learn.basicTests.dto;

import com.learn.basicTests.model.Person.TypePersonEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class PersonDTO {

    String name;
    String cpf;
    TypePersonEnum typePersonEnum;
}
