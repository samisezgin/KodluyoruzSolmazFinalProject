package com.samisezgin.finalproject.dto.request;

import com.samisezgin.finalproject.model.enums.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public class TicketRequest {
    private String citizenshipNumber;
    private String passengerName;
    private String passengerSurname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public TicketRequest() {
    }

    public TicketRequest(String citizenshipNumber, String passengerName, String passengerSurname, Gender gender) {
        this.citizenshipNumber = citizenshipNumber;
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.gender = gender;
    }

    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    public void setCitizenshipNumber(String citizenshipNumber) {
        this.citizenshipNumber = citizenshipNumber;
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
