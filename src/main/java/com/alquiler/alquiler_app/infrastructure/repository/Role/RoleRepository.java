package com.alquiler.alquiler_app.infrastructure.repository.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquiler.alquiler_app.domain.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
