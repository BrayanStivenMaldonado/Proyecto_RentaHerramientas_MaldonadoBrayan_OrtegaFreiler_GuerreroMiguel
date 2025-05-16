package com.alquiler.alquiler_app.application.service;

import java.util.List;

import com.alquiler.alquiler_app.domain.entities.Tool;

public interface ToolService {
    List<Tool> getAllTools();
    Tool getToolById(Long id);
    Tool createTool(Tool tool);
    Tool updateTool(Long id, Tool tool);
    void deleteTool(Long id);
    List<Tool> searchTools(String name);
}
