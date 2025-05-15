package com.alquiler.alquiler_app.domain.entities;

import java.util.List;

import javax.management.Notification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String idNumber;
    private String phone;
    private String email;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "person")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "person")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "person")
    private List<Payment> payments;
}
