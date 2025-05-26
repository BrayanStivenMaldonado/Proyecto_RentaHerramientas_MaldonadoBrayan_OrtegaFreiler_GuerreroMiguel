package com.alquiler.alquiler_app.application.service;

import java.util.List;
import java.util.Optional;

import com.alquiler.alquiler_app.domain.DTOs.PaymentResponse;

public interface PaymentService {
    List<Payment> getAllPayments();

    Optional<Payment> getById(Long idf);
    
}
