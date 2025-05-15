package com.alquiler.alquiler_app.domain.entities;

import java.time.LocalDate;
import java.util.List;

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

    public Reservation() {
    }

    public Reservation(Long id, Person user, LocalDate rentalDate, LocalDate returnDate, List<ReservationTool> tools,
            Return returnInfo, Payment payment) {
        this.id = id;
        this.user = user;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.tools = tools;
        this.returnInfo = returnInfo;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public List<ReservationTool> getTools() {
        return tools;
    }

    public void setTools(List<ReservationTool> tools) {
        this.tools = tools;
    }

    public Return getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(Return returnInfo) {
        this.returnInfo = returnInfo;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}