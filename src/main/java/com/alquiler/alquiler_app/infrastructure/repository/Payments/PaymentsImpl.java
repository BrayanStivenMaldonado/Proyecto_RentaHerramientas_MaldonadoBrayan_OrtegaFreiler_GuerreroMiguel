package com.alquiler.alquiler_app.infrastructure.repository.Payments;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.alquiler_app.application.service.PaymentService;
import com.alquiler.alquiler_app.domain.DTOs.PaymentResponse;
import com.alquiler.alquiler_app.domain.entities.Payment;

@Service
public class PaymentsImpl implements PaymentService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentsRepository.findAll()
                .stream()
                .map(PaymentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Payment> getById(Long idf) {
       return paymentsRepository.findById(idf);
    }
    
}
