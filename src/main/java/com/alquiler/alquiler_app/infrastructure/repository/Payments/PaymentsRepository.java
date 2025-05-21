package com.alquiler.alquiler_app.infrastructure.repository.Payments;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquiler.alquiler_app.domain.entities.Payment;

public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    
}
