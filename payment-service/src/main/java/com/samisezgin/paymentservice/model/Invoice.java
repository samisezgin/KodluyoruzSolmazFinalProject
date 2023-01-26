package com.samisezgin.paymentservice.model;


import com.samisezgin.paymentservice.model.enums.PaymentType;

import javax.persistence.*;

@Entity
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Double paymentTotal;
    private Integer bookingId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(Double paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
}
