package com.alquiler.alquiler_app.application.service;

import java.util.List;
import java.util.Optional;

import com.alquiler.alquiler_app.domain.entities.Tool;

public interface ToolService {
    List<Tool> getAllTools();
    Optional<Tool> getToolById(Long id);
    Tool saveTool(Tool tool);
    Optional<Tool> updateTool(Long id, Tool tool);
    Optional<Tool> deleteTool(Long id);
    List<Tool> searchTools(String name);
} 