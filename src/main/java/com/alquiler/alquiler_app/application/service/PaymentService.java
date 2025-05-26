package com.alquiler.alquiler_app.application.service;

import java.util.List;
import java.util.Optional;

import com.alquiler.alquiler_app.domain.DTOs.PaymentResponse;
import com.alquiler.alquiler_app.domain.entities.Payment;

public interface PaymentService {
    List<PaymentResponse> getAllPayments();

    Optional<Payment> getById(Long idf);
    
}
