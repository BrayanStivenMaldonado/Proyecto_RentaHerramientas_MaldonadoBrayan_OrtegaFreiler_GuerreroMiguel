package com.alquiler.alquiler_app.domain.DTOs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alquiler.alquiler_app.domain.entities.Payment;

public class PaymentDTO {
    private Long id;
    private String paymentMethod;
    private LocalDate date;
    private Double subtotal;
    private Double total;
    private Long reservationId;
    private List<String> toolNames;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.paymentMethod = payment.getPaymentMethod();
        this.date = payment.getDate();
        this.subtotal = payment.getSubtotal();
        this.total = payment.getTotal();

        if (payment.getReservation() != null) {
            this.reservationId = payment.getReservation().getId();
            this.toolNames = payment.getReservation().getTools() != null ? payment.getReservation().getTools()
                    .stream()
                    .map(rt -> rt.getTool().getToolName())
                    .collect(Collectors.toList()) : new ArrayList<>();
        } else {
            this.reservationId = null;
            this.toolNames = new ArrayList<>();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public List<String> getToolNames() {
        return toolNames;
    }

    public void setToolNames(List<String> toolNames) {
        this.toolNames = toolNames;
    }
}