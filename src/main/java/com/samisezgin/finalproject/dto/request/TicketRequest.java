package com.samisezgin.finalproject.dto.request;

import com.samisezgin.finalproject.model.enums.Gender;
import javax.persistence.*;


public class TicketRequest {
    private String citizenshipNumber;
    private String passengerName;
    private String passengerSurname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public TicketRequest() {
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
