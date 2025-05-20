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

import com.alquiler.alquiler_app.Exceptions.ResourceNotFoundException;
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
        Person person = personService.getPersonById(id)
        .orElseThrow(()-> new ResourceNotFoundException("persona con ID"+id+"no se pudo encontrar"));
        
       return ResponseEntity.ok(person);
    }
    
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonRequestDTO personRequestDTO){
        Role role = roleService.findById(personRequestDTO.getRoleId())
        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        Person person = new Person();
        person.setFirstName(personRequestDTO.getFirstName());
        person.setLastName(personRequestDTO.getLastName());
        person.setIdNumber(personRequestDTO.getIdNumber());
        person.setEmail(personRequestDTO.getEmail());
        person.setRole(role);

        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody PersonRequestDTO person){
        Person personupdate = personService.getPersonById(id)
        .orElseThrow(()-> new ResourceNotFoundException("persona con ID"+id+"no encontrada"));

        Role role = roleService.findById(person.getRoleId())
        .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + person.getRoleId() + " no encontrado"));
       
        personupdate.setFirstName(person.getFirstName());
        personupdate.setLastName(person.getLastName());
        personupdate.setIdNumber(person.getIdNumber());
        personupdate.setEmail(person.getEmail());
        personupdate.setRole(role); 

      Person updatedPerson = personService.savePerson(personupdate);

      return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        Person person = personService.getPersonById(id)
        .orElseThrow(()-> new ResourceNotFoundException("persona con ID "+id+" no fue encontrada "));

        personService.deletePerson(id);
       
        return ResponseEntity.notFound().build();
    }
}