package com.samisezgin.finalproject.dto.response;

import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.model.enums.TravelType;

public class BookingResponse {

    public String userEmail;
    public TicketRequest[] ticketList;

    private String fromCity;

    private String toCity;


    private String voyageDateTime;

    private TravelType travelType;


    public BookingResponse() {
    }

    public BookingResponse(String userEmail, TicketRequest[] ticketList, String fromCity, String toCity, String voyageDateTime, TravelType travelType) {
        this.userEmail = userEmail;
        this.ticketList = ticketList;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.voyageDateTime = voyageDateTime;
        this.travelType = travelType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public TicketRequest[] getTicketList() {
        return ticketList;
    }

    public void setTicketList(TicketRequest[] ticketList) {
        this.ticketList = ticketList;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getVoyageDateTime() {
        return voyageDateTime;
    }

    public void setVoyageDateTime(String voyageDateTime) {
        this.voyageDateTime = voyageDateTime;
    }

    public TravelType getTravelType() {
        return travelType;
    }

    public void setTravelType(TravelType travelType) {
        this.travelType = travelType;
    }
}
