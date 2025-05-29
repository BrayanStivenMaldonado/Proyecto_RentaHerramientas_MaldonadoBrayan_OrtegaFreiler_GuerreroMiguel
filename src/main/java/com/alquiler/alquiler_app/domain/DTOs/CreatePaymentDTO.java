package com.alquiler.alquiler_app.domain.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class CreatePaymentDTO {
    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long reservationId;

    @NotNull(message = "El método de pago es obligatorio")
    private String paymentMethod;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotNull(message = "El subtotal es obligatorio")
    private Double subtotal;

    @NotNull(message = "El total es obligatorio")
    private Double total;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    
}
