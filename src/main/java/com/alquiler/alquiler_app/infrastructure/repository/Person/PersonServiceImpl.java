package com.alquiler.alquiler_app.infrastructure.repository.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.alquiler_app.Exceptions.ResourceNotFoundException;
import com.alquiler.alquiler_app.application.service.PersonService;
import com.alquiler.alquiler_app.application.service.RoleService;
import com.alquiler.alquiler_app.domain.DTOs.PersonRequestDTO;
import com.alquiler.alquiler_app.domain.entities.Person;
import com.alquiler.alquiler_app.domain.entities.Role;
import com.alquiler.alquiler_app.infrastructure.repository.Role.RoleRepository;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        Optional<Role> optionalRoleUser = roleRepository.findByName("USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if (person.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        if (person.isProvider()) {
            Optional<Role> optionalRoleProvider = roleRepository.findByName("PROVIDER");
            optionalRoleProvider.ifPresent(roles::add);
        }

        person.setRoles(roles);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Transactional
    @Override
    public Person updatePerson(Long id, PersonRequestDTO personRequestDTO) {
        Person existingPerson = personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Persona con ID " + id + " no fue encontrada"));

        List<Role> roles = personRequestDTO.getRoleIds().stream()
            .map(roleId -> roleService.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + roleId + " no fue encontrado")))
            .toList();

        existingPerson.setFirstName(personRequestDTO.getFirstName());
        existingPerson.setLastName(personRequestDTO.getLastName());
        existingPerson.setIdNumber(personRequestDTO.getIdNumber());
        existingPerson.setPhone(personRequestDTO.getPhone());
        existingPerson.setEmail(personRequestDTO.getEmail());
        existingPerson.setRoles(roles);

        if (personRequestDTO.getPassword() != null && !personRequestDTO.getPassword().isEmpty()) {
            existingPerson.setPassword(passwordEncoder.encode(personRequestDTO.getPassword()));
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
    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }
}