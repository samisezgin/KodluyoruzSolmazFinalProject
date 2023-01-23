package com.samisezgin.finalproject.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> ticketList;

    private final LocalDateTime creationDateTime = LocalDateTime.now();

    private double bookingTotalPrice;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private PassengerUser passengerUser;

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

    public PassengerUser getPassengerUser() {
        return passengerUser;
    }

    public void setPassengerUser(PassengerUser passengerUser) {
        this.passengerUser = passengerUser;
    }
}
