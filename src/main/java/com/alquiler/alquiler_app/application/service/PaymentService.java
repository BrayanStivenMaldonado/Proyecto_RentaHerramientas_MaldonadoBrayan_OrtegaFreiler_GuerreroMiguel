package com.alquiler.alquiler_app.application.service;

import java.util.List;

import com.alquiler.alquiler_app.domain.DTOs.PaymentResponse;

public interface PaymentService {
    List<PaymentResponse> getAllPayments();
}
