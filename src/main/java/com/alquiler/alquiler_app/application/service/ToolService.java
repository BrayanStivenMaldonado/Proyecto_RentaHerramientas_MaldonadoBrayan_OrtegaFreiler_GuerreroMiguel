package com.alquiler.alquiler_app.application.service;

import java.util.List;

import com.alquiler.alquiler_app.domain.DTOs.ToolRequestDTO;
import com.alquiler.alquiler_app.domain.entities.Tool;

public interface ToolService {
    List<Tool> getAllTools();
    Tool getToolById(Long id);
    Tool saveTool(Tool tool);
    Tool updateTool(Long id, ToolRequestDTO toolRequestDTO);
    void deleteTool(Long id);
    List<Tool> searchTools(String name);
} 