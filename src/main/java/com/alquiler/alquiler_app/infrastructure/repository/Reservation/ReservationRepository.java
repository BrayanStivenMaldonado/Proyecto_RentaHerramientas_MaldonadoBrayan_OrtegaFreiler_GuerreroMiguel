package com.alquiler.alquiler_app.infrastructure.repository.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquiler.alquiler_app.domain.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
}
