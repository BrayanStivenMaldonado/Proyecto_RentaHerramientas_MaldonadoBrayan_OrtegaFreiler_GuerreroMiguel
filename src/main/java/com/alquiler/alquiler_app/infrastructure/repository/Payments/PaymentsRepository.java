package com.alquiler.alquiler_app.infrastructure.repository.Payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.alquiler_app.domain.entities.Payment;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    
}
