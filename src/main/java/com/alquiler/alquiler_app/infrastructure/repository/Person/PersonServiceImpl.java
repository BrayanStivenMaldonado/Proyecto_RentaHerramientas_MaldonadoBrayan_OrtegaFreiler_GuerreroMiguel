package com.alquiler.alquiler_app.infrastructure.repository.Person;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.alquiler_app.Exceptions.ResourceNotFoundException;
import com.alquiler.alquiler_app.application.service.PersonService;
import com.alquiler.alquiler_app.application.service.RoleService;
import com.alquiler.alquiler_app.domain.DTOs.PersonRequestDTO;
import com.alquiler.alquiler_app.domain.entities.Person;
import com.alquiler.alquiler_app.domain.entities.Role;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    RoleService roleService;

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
    public Person updatePerson(Long id, PersonRequestDTO personRequestDTO) {
    Person existingPerson = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Persona con ID " + id + " no fue encontrada"));

    Role role = roleService.findById(personRequestDTO.getRoleId())
        .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + personRequestDTO.getRoleId() + " no fue encontrado"));

    existingPerson.setFirstName(personRequestDTO.getFirstName());
    existingPerson.setLastName(personRequestDTO.getLastName());
    existingPerson.setIdNumber(personRequestDTO.getIdNumber());
    existingPerson.setEmail(personRequestDTO.getEmail());
    existingPerson.setRole(role);

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
}