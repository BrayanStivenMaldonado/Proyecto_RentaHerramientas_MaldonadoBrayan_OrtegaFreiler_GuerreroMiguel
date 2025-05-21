package com.alquiler.alquiler_app.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tool_id")
    private Tool tool;

    private Integer rentals;
    private Double income;
    private LocalDateTime lastUpdated;
    
    public Report() {
    }

    public Report(Long id, Tool tool, Integer rentals, Double income, LocalDateTime lastUpdated) {
        this.id = id;
        this.tool = tool;
        this.rentals = rentals;
        this.income = income;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Integer getRentals() {
        return rentals;
    }

    public void setRentals(Integer rentals) {
        this.rentals = rentals;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    } 
}