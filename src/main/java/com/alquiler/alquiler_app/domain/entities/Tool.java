package com.alquiler.alquiler_app.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tool")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toolName;
    private String image;
    private String usage;
    private Double rentalPrice;
    private Double replacementPrice;
    private Integer quantity;

    @OneToMany(mappedBy = "tool")
    private List<ReservationTool> reservations;

    @OneToOne(mappedBy = "tool")
    private Report report;

    public Tool() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Double getReplacementPrice() {
        return replacementPrice;
    }

    public void setReplacementPrice(Double replacementPrice) {
        this.replacementPrice = replacementPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<ReservationTool> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationTool> reservations) {
        this.reservations = reservations;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}