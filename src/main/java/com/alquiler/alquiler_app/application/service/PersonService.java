package com.alquiler.alquiler_app.application.service;

import java.util.List;
import java.util.Optional;

import com.alquiler.alquiler_app.domain.DTOs.PersonRequestDTO;
import com.alquiler.alquiler_app.domain.entities.Person;

public interface PersonService {
    List<Person> getAllPersons();
    Optional<Person> getPersonById(Long id);
    Person savePerson(Person person);
    Person updatePerson(Long id, PersonRequestDTO personRequestDTO);
    void deletePerson(Long id);
    List<Person> searchPersons(String name);
    boolean existsByUsername(String Username);
}