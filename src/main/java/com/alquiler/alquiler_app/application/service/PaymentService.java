package com.alquiler.alquiler_app.application.service;

import java.util.List;

import com.alquiler.alquiler_app.domain.entities.Payment;

public interface PaymentService {
    List<Payment> getAllPayments();
    
}
