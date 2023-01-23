package com.samisezgin.finalproject.dto.response;

import com.samisezgin.finalproject.model.enums.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TicketResponse {

    private String trIdNo;
    private String passengerName;
    private String passengerSurname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Double price;

    public TicketResponse() {
    }

    public TicketResponse(String trIdNo, String passengerName, String passengerSurname, Gender gender, Double price) {
        this.trIdNo = trIdNo;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.gender = gender;
        this.price = price;
    }

    public String getTrIdNo() {
        return trIdNo;
    }

    public void setTrIdNo(String trIdNo) {
        this.trIdNo = trIdNo;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
