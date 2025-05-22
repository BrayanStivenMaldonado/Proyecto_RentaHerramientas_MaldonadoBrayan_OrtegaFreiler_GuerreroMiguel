package com.alquiler.alquiler_app.infrastructure.repository.Person;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.alquiler_app.domain.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    List<Person> findByFirstName(String firstName);
    boolean existsByUsername(String Username);
    Optional<Person> findByUsername(String username);
}
