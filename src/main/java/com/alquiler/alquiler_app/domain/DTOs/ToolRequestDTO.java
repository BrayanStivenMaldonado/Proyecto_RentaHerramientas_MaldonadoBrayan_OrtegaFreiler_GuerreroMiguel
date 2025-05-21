package com.alquiler.alquiler_app.domain.DTOs;

public class ToolRequestDTO {
    private String toolName;
    private String image;
    private String usage;
    private Double rentalPrice;
    private Double replacementPrice;
    private Integer quantity;

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
}