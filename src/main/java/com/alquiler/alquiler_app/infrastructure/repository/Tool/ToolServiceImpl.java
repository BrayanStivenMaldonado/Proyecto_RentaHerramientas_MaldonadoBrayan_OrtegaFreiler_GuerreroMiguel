package com.alquiler.alquiler_app.infrastructure.repository.Tool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.alquiler_app.Exceptions.ResourceNotFoundException;
import com.alquiler.alquiler_app.application.service.ToolService;
import com.alquiler.alquiler_app.domain.DTOs.ToolRequestDTO;
import com.alquiler.alquiler_app.domain.entities.Tool;


@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    private ToolRepository toolRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Tool> getAllTools() {
        return (List<Tool>)toolRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Tool getToolById(Long id) {
        return toolRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Herramienta con ID "+id+" no encontrada"));
    }

    @Transactional
    @Override
    public Tool saveTool(Tool tool){
        return toolRepository.save(tool);
    }
    
    @Transactional
    @Override
    public Tool updateTool(Long id, ToolRequestDTO toolRequestDTO) {
        Tool tool = getToolById(id);
        tool.setToolName(toolRequestDTO.getToolName());
        tool.setImage(toolRequestDTO.getImage());
        tool.setUsage(toolRequestDTO.getUsage());
        tool.setRentalPrice(toolRequestDTO.getRentalPrice());
        tool.setReplacementPrice(toolRequestDTO.getReplacementPrice());
        tool.setQuantity(toolRequestDTO.getQuantity());

        return toolRepository.save(tool);
    }

    @Transactional
    @Override
    public void deleteTool(Long id) {
        Tool tool = toolRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Herramienta con ID " + id + " no encontrada"));
        toolRepository.delete(tool);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tool> searchTools(String name) {
        return toolRepository.findByToolName(name);
    }
}