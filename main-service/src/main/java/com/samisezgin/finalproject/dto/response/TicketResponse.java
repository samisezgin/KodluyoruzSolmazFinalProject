package com.samisezgin.finalproject.dto.response;

import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.TravelType;
import javax.persistence.*;

import java.time.LocalDateTime;

public class TicketResponse {

    private String citizenshipNumber;
    private String passengerName;
    private String passengerSurname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Double price;

    private String voyageFromCity;

    private String voyageToCity;

    private LocalDateTime voyageDateTime;

    private TravelType voyageTravelType;

    public TicketResponse() {
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

    public String getVoyageFromCity() {
        return voyageFromCity;
    }

    public void setVoyageFromCity(String voyageFromCity) {
        this.voyageFromCity = voyageFromCity;
    }

    public String getVoyageToCity() {
        return voyageToCity;
    }

    public void setVoyageToCity(String voyageToCity) {
        this.voyageToCity = voyageToCity;
    }

    public LocalDateTime getVoyageDateTime() {
        return voyageDateTime;
    }

    public void setVoyageDateTime(LocalDateTime voyageDateTime) {
        this.voyageDateTime = voyageDateTime;
    }

    public TravelType getVoyageTravelType() {
        return voyageTravelType;
    }

    public void setVoyageTravelType(TravelType voyageTravelType) {
        this.voyageTravelType = voyageTravelType;
    }

    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    public void setCitizenshipNumber(String citizenshipNumber) {
        this.citizenshipNumber = citizenshipNumber;
    }
}
