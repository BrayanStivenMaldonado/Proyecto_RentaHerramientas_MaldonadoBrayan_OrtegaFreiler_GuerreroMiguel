package com.alquiler.alquiler_app.infrastructure.repository.Role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.alquiler.alquiler_app.application.service.RoleService;
import com.alquiler.alquiler_app.domain.entities.Role;

public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();        
    }
}
