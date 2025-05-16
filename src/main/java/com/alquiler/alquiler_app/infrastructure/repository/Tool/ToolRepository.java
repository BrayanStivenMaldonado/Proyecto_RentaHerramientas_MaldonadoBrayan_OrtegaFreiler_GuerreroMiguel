package com.alquiler.alquiler_app.infrastructure.repository.Tool;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.alquiler_app.domain.entities.Tool;
import java.util.List;


@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    List<Tool> findByToolName(String name);
}
