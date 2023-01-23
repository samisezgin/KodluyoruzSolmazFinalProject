package com.samisezgin.finalproject.dto.request;

import com.samisezgin.finalproject.model.enums.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TicketRequest {
    private String trIdNo;
    private String passengerName;
    private String passengerSurname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public TicketRequest() {
    }

    public TicketRequest(String trIdNo, String passengerName, String passengerSurname, Gender gender) {

        this.trIdNo = trIdNo;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.gender = gender;
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
}
