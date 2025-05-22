package com.alquiler.alquiler_app.infrastructure.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.alquiler_app.application.service.ToolService;
import com.alquiler.alquiler_app.domain.DTOs.ToolRequestDTO;
import com.alquiler.alquiler_app.domain.entities.Tool;

@RestController
@RequestMapping(value = "/api/tools")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @GetMapping
    public List<Tool> getAllTools(){
        return toolService.getAllTools();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id){
        Tool tool = toolService.getToolById(id);
        return ResponseEntity.ok(tool);
    }
    
    @PostMapping
    public ResponseEntity<Tool> createTool(@RequestBody ToolRequestDTO toolRequestDTO){
        Tool tool = new Tool();
        tool.setToolName(toolRequestDTO.getToolName());
        tool.setImage(toolRequestDTO.getImage());
        tool.setcategory(toolRequestDTO.getUsage());
        tool.setRentalPrice(toolRequestDTO.getRentalPrice());
        tool.setReplacementPrice(toolRequestDTO.getReplacementPrice());
        tool.setQuantity(toolRequestDTO.getQuantity());

        Tool savedTool = toolService.saveTool(tool);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTool);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tool> updateTool(@PathVariable Long id, @RequestBody ToolRequestDTO toolRequestDTO) {
        Tool updatedTool = toolService.updateTool(id, toolRequestDTO);
        return ResponseEntity.ok(updatedTool);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTool(@PathVariable Long id){
        toolService.deleteTool(id);
        return ResponseEntity.noContent().build();
    } 
}