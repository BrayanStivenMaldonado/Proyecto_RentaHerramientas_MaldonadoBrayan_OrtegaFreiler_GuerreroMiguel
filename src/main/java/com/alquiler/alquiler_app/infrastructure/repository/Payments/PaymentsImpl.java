package com.alquiler.alquiler_app.infrastructure.repository.Payments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.alquiler_app.application.service.PaymentService;
import com.alquiler.alquiler_app.domain.entities.Payment;

@Service
public class PaymentsImpl implements PaymentService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Payment> getAllPayments() {
        return (List<Payment>)paymentsRepository.findAll();
    }
    
}
