package com.alquiler.alquiler_app.infrastructure.repository.Person;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.alquiler_app.Exceptions.ResourceNotFoundException;
import com.alquiler.alquiler_app.application.service.PersonService;
import com.alquiler.alquiler_app.domain.DTOs.PersonRequestDTO;
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
        if (person.getPassword() == null || person.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }

        if (person.getRole() == null) {
            throw new IllegalArgumentException("Debe asignar un rol a la persona.");
        }

        return personRepository.save(person);
    }

    @Transactional
    @Override
    public Person updatePerson(Long id, PersonRequestDTO personRequestDTO) {
        Person existingPerson = personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Persona con ID " + id + " no fue encontrada"));

        existingPerson.setFirstName(personRequestDTO.getFirstName());
        existingPerson.setLastName(personRequestDTO.getLastName());
        existingPerson.setusername(personRequestDTO.getUsername());
        existingPerson.setRole(personRequestDTO.getRole());

        if (personRequestDTO.getPassword() != null && !personRequestDTO.getPassword().isBlank()) {
            existingPerson.setPassword(personRequestDTO.getPassword()); // sin cifrado
        }

        return personRepository.save(existingPerson);
    }

    @Transactional
    @Override
    public void deletePerson(Long id){
        Person person = personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Persona con ID "+id+" no fue encontrada"));
        personRepository.delete(person);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> searchPersons(String name) {
       return personRepository.findByFirstName(name);
    }

    @Override
    public boolean existsByUsername(String username) {
        return personRepository.existsByUsername(username);
    }
}