package com.alquiler.alquiler_app.application.service;

import java.util.List;
import java.util.Optional;

import com.alquiler.alquiler_app.domain.entities.Role;

public interface RoleService {
    Optional<Role> findById(Long id);
    List<Role> findAll();
}
