package com.alquiler.alquiler_app.infrastructure.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.alquiler_app.domain.entities.Person;
import com.alquiler.alquiler_app.domain.entities.Reservation;
import com.alquiler.alquiler_app.infrastructure.repository.Person.PersonRepository;
import com.alquiler.alquiler_app.infrastructure.repository.Reservation.ReservationRepository;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    private final ReservationRepository reservationRepository;
    private final PersonRepository personRepository;

    public ReservationController(ReservationRepository reservationRepository, PersonRepository personRepository) {
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        if (reservation.getUser() !=null) {
            Person user = personRepository.findById(reservation.getUser().getId()).orElse(null);
            reservation.setUser(user);
        }
        return reservationRepository.save(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setRentalDate(updatedReservation.getRentalDate());
            reservation.setReturnDate(updatedReservation.getReturnDate());

            if (updatedReservation.getUser() != null) {
                Long userId = updatedReservation.getUser().getId();
                Person user = personRepository.findById(updatedReservation.getUser().getId()).orElse(null);
                reservation.setUser(user);
            }
            return reservationRepository.save(reservation);
        }).orElse(null);
    }
    
}
