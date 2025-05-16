package com.alquiler.alquiler_app.infrastructure.repository.Tool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.alquiler_app.application.service.ToolService;
import com.alquiler.alquiler_app.domain.entities.Tool;

@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    private ToolRepository toolRepository;

    @Override
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    @Override
    public Tool getToolById(Long id) {
        return toolRepository.findById(id).orElseThrow(() -> new RuntimeException("La herramienta con id: "+id+" no se pudo encontrar"));
    }

    @Override
    public Tool createTool(Tool tool) {
        return toolRepository.save(tool);
    }

    @Override
    public Tool updateTool(Long id, Tool tool) {
        Tool existing = getToolById(id);
        existing.setToolName(tool.getToolName());
        existing.setImage(tool.getImage());
        existing.setUsage(tool.getUsage());
        existing.setRentalPrice(tool.getRentalPrice());
        existing.setQuantity(tool.getQuantity());
        return toolRepository.save(existing);

    }

    @Override
    public void deleteTool(Long id) {
        toolRepository.deleteById(id);
    }

    @Override
    public List<Tool> searchTools(String name) {
        return toolRepository.findByToolName(name);
    }
}
