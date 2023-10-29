package com.learn.basicTests.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private TypePersonEnum typePersonEnum;

    public enum TypePersonEnum {
        SENDER,
        RECIPIENT
    }

}
