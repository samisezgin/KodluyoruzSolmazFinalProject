package com.samisezgin.finalproject.model;

import com.samisezgin.finalproject.model.enums.PaymentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    private final LocalDateTime creationDateTime = LocalDateTime.now();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketList;
    private double bookingTotalPrice;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private User passengerUser;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private boolean isActive = true;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public double getBookingTotalPrice() {
        return bookingTotalPrice;
    }

    public void setBookingTotalPrice(double bookingTotalPrice) {
        this.bookingTotalPrice = bookingTotalPrice;
    }

    public User getPassengerUser() {
        return passengerUser;
    }

    public void setPassengerUser(User passengerUser) {
        this.passengerUser = passengerUser;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
