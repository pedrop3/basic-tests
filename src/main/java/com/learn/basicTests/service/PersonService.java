package com.learn.basicTests.service;

import com.learn.basicTests.model.Person;

public interface PersonService  {

    Person save(Person person);

    Person findById(Long personId);
}