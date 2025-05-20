package com.alquiler.alquiler_app.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.alquiler_app.application.service.PersonService;
import com.alquiler.alquiler_app.application.service.RoleService;
import com.alquiler.alquiler_app.domain.DTOs.PersonRequestDTO;
import com.alquiler.alquiler_app.domain.entities.Person;
import com.alquiler.alquiler_app.domain.entities.Role;

@RestController
@RequestMapping(value = "/api/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    private RoleService roleService;

    @GetMapping
    public List<Person> getAllTools(){
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> personOptional = personService.getPersonById(id);
        if(personOptional.isPresent()){
            return ResponseEntity.ok(personOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonRequestDTO personRequestDTO){
        Role role = roleService.findById(personRequestDTO.getRoleId())
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Person person = new Person();
        person.setFirstName(personRequestDTO.getFirstName());
        person.setLastName(personRequestDTO.getLastName());
        person.setIdNumber(person.getIdNumber());
        person.setEmail(person.getEmail());
        person.setRole(role);

        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTool(@PathVariable Long id, @RequestBody Person person){
        Optional<Person> personOptional = personService.getPersonById(id);
        if(personOptional.isPresent()){
            Person updatePerson = personOptional.orElseThrow();
            updatePerson.setFirstName(person.getFirstName());
            return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(updatePerson));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        Optional<Person> personOptional = personService.getPersonById(id);
        if(personOptional.isPresent()){
            return ResponseEntity.ok(personOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}