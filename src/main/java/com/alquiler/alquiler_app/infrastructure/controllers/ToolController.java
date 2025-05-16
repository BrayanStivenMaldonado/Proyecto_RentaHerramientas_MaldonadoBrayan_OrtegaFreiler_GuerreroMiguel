package com.alquiler.alquiler_app.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.alquiler_app.application.service.ToolService;
import com.alquiler.alquiler_app.domain.entities.Tool;

@RestController
@RequestMapping(value = "/api")
public class ToolController {
    @Autowired
    private ToolService toolService;

    @GetMapping("/tools")
    public List<Tool> getAllTools(){
        return toolService.getAllTools();
    }
}