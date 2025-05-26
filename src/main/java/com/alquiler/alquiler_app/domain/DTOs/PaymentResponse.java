package com.alquiler.alquiler_app.domain.DTOs;

import com.alquiler.alquiler_app.domain.entities.Payment;

public class PaymentResponse {
    private Long id;
    private String paymentMethod;
    private String date;
    private Double subtotal;
    private Double total;
    private String username;

    public PaymentResponse(Payment payment) {
        this.id = payment.getId();
        this.paymentMethod = payment.getPaymentMethod();
        this.date = payment.getDate().toString();
        this.subtotal = payment.getSubtotal();
        this.total = payment.getTotal();

        if (payment.getReservation() != null &&
            payment.getReservation().getUser() != null) {
            this.username = payment.getReservation().getUser().getFirstName() +" "+ payment.getReservation().getUser().getLastName();
        } else {
            this.username = "Desconocido";
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}