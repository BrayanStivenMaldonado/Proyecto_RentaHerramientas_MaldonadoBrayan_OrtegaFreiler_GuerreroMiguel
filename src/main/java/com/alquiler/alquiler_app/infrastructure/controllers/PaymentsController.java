package com.alquiler.alquiler_app.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alquiler.alquiler_app.application.service.PaymentService;
import com.alquiler.alquiler_app.domain.DTOs.CreatePaymentDTO;
import com.alquiler.alquiler_app.domain.DTOs.PaymentDTO;
import com.alquiler.alquiler_app.domain.entities.Payment;
import com.alquiler.alquiler_app.domain.entities.Reservation;
import com.alquiler.alquiler_app.infrastructure.repository.Payments.PaymentsRepository;
import com.alquiler.alquiler_app.infrastructure.repository.Reservation.ReservationRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentsRepository paymentsRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    PaymentService paymentService;

    public PaymentsController(PaymentsRepository paymentsRepository, ReservationRepository reservationRepository) {
        this.paymentsRepository = paymentsRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentsRepository.findAll();

        return payments.stream()
                .map(PaymentDTO::new)
                .toList();
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody CreatePaymentDTO dto) {
        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));
        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setDate(dto.getDate());
        payment.setSubtotal(dto.getSubtotal());
        payment.setTotal(dto.getTotal());

        Payment savedPayment = paymentsRepository.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentDTO(savedPayment));
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentsRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentsRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        return paymentsRepository.findById(id).map(payment -> {
            payment.setPaymentMethod(updatedPayment.getPaymentMethod());
            payment.setDate(updatedPayment.getDate());
            payment.setSubtotal(updatedPayment.getSubtotal());
            payment.setTotal(updatedPayment.getTotal());

            if (updatedPayment.getReservation() != null) {
                Long reservationId = updatedPayment.getReservation().getId();
                Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
                if (reservation != null) {
                    payment.setReservation(reservation);
                }
            }

            return paymentsRepository.save(payment);
        }).orElse(null);
    }
}