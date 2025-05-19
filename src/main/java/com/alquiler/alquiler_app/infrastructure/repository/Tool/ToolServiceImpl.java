package com.alquiler.alquiler_app.infrastructure.repository.Tool;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.alquiler_app.application.service.ToolService;
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
    public Optional<Tool> getToolById(Long id) {
        return toolRepository.findById(id);
    }

    @Transactional
    @Override
    public Tool saveTool(Tool tool){
        return toolRepository.save(tool);
    }
    
    @Transactional
    @Override
    public Optional<Tool> updateTool(Long id, Tool tool) {
        return toolRepository.findById(id).map(toolDb ->{
            toolDb.setToolName(tool.getToolName());
            toolDb.setImage(tool.getImage());
            toolDb.setUsage(tool.getUsage());
            toolDb.setRentalPrice(tool.getRentalPrice());
            toolDb.setQuantity(tool.getQuantity());
            return toolRepository.save(toolDb);
        });
    }

    @Transactional
    @Override
    public Optional<Tool> deleteTool(Long id) {
        return toolRepository.findById(id).map(toolDb -> {
            toolRepository.delete(toolDb);
            return toolDb;
        });
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tool> searchTools(String name) {
        return toolRepository.findByToolName(name);
    }
}