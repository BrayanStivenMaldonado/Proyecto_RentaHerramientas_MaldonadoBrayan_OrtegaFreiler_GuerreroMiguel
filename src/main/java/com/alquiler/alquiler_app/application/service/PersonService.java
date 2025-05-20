package com.alquiler.alquiler_app.application.service;

import java.util.List;
import java.util.Optional;

import com.alquiler.alquiler_app.domain.entities.Person;

public interface PersonService {
    List<Person> getAllPersons();
    Optional<Person> getPersonById(Long id);
    Person savePerson(Person person);
    Optional<Person> updatePerson(Long id, Person person);
    Optional<Person> deletePerson(Long id);
    List<Person> searchPersons(String name);
}