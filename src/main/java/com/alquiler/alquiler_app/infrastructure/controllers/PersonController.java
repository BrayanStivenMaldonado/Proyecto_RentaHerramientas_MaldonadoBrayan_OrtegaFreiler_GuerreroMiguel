package com.alquiler.alquiler_app.infrastructure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.alquiler.alquiler_app.domain.DTOs.PersonRequestDTO;
import com.alquiler.alquiler_app.domain.entities.Person;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id)
        .orElseThrow(()-> new ResourceNotFoundException("persona con ID "+id+" no se pudo encontrar"));
        
       return ResponseEntity.ok(person);
    }
    
    // @PostMapping
    // public ResponseEntity<?> createPerson(@RequestBody PersonRequestDTO personRequestDTO){
    //     List<Role> roles = personRequestDTO.getRoleIds().stream()
    //     .map(id -> roleService.findById(id)
    //         .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + id + " no encontrado"))
    //     ).toList();

    //     Person person = new Person();
    //     person.setFirstName(personRequestDTO.getFirstName());
    //     person.setLastName(personRequestDTO.getLastName());
    //     person.setIdNumber(personRequestDTO.getIdNumber());
    //     person.setPhone(personRequestDTO.getPhone());
    //     person.setEmail(personRequestDTO.getEmail());
    //     person.setPassword(passwordEncoder.encode(personRequestDTO.getPassword()));
    //     person.setAdmin(personRequestDTO.isAdmin());
    //     person.setRoles(roles);

    //     Person savedPerson = personService.savePerson(person);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    // }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(person));
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody PersonRequestDTO personDto){
       Person updatedPerson = personService.updatePerson(id, personDto);
       return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}