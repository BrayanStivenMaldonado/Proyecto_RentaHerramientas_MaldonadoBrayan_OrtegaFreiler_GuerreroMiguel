package com.alquiler.alquiler_app.infrastructure.repository.Person;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.alquiler_app.application.service.PersonService;
import com.alquiler.alquiler_app.domain.entities.Person;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Person> getAllPersons() {
        return (List<Person>)personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Person> getPersonById(Long id) {
       return personRepository.findById(id);
    }

    @Transactional
    @Override
    public Person savePerson(Person person) {
       return personRepository.save(person);
    }

    @Transactional
    @Override
    public Optional<Person> updatePerson(Long id, Person person) {
        return personRepository.findById(id).map(personDb ->{
            personDb.setFirstName(person.getFirstName());
            personDb.setLastName(person.getLastName());
            personDb.setIdNumber(person.getIdNumber());
            personDb.setPhone(person.getPhone());
            personDb.setEmail(person.getEmail());
            personDb.setRole(person.getRole());
            return personRepository.save(personDb);
        });
    }

    @Transactional
    @Override
    public Optional<Person> deletePerson(Long id) {
        return personRepository.findById(id).map(personDb -> {
            personRepository.delete(personDb);
            return personDb;
        });
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> searchPersons(String name) {
       return personRepository.findByFirstName(name);
    }
}