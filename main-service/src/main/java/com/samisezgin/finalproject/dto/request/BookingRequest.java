package com.samisezgin.finalproject.dto.request;


import com.samisezgin.finalproject.model.enums.TravelType;

public class BookingRequest {

    private String passengerUserEmail;
    private TicketRequest[] bookingListTicketList;

    private String fromCity;

    private String toCity;

    private String voyageDateTime;

    private TravelType travelType;

    public BookingRequest()
    {

    }

    public BookingRequest(String passengerUserEmail, TicketRequest[] bookingListTicketList, String fromCity, String toCity, String voyageDateTime, TravelType travelType) {
        this.passengerUserEmail = passengerUserEmail;
        this.bookingListTicketList = bookingListTicketList;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.voyageDateTime = voyageDateTime;
        this.travelType = travelType;
    }

    public BookingRequest(String passengerUserEmail) {
        this.passengerUserEmail = passengerUserEmail;
    }

    public String getPassengerUserEmail() {
        return passengerUserEmail;
    }

    public void setPassengerUserEmail(String passengerUserEmail) {
        this.passengerUserEmail = passengerUserEmail;
    }

    public TicketRequest[] getBookingListTicketList() {
        return bookingListTicketList;
    }

    public void setBookingListTicketList(TicketRequest[] bookingListTicketList) {
        this.bookingListTicketList = bookingListTicketList;
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
