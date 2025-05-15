package com.alquiler.alquiler_app.domain.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Person user;

    private LocalDate rentalDate;
    private LocalDate returnDate;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationTool> tools;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Return returnInfo;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Payment payment;    

    

}
