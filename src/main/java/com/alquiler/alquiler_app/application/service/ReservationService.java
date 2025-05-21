package com.alquiler.alquiler_app.application.service;

import com.alquiler.alquiler_app.domain.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> getAll();
    Optional<Reservation> getById(Long id);
    Reservation create(Reservation reservation);
    Reservation update(Long id, Reservation reservation);
    void delete(Long id);
}
