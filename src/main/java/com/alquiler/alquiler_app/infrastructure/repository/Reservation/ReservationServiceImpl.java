package com.alquiler.alquiler_app.infrastructure.repository.Reservation;

import com.alquiler.alquiler_app.application.service.ReservationService;
import com.alquiler.alquiler_app.domain.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Override
    public List<Reservation> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Reservation> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Reservation create(Reservation reservation) {
        return repository.save(reservation);
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {
        reservation.setId(id);
        return repository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
