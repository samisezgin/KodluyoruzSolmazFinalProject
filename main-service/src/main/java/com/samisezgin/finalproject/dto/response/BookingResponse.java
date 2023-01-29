package com.samisezgin.finalproject.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponse {
    private String userEmail;
    private List<TicketResponse> ticketResponseList;

    private LocalDateTime creationDateTime;

    private Double bookingTotalPrice;

    public BookingResponse() {
    }

    public BookingResponse(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<TicketResponse> getTicketResponseList() {
        return ticketResponseList;
    }

    public void setTicketResponseList(List<TicketResponse> ticketResponseList) {
        this.ticketResponseList = ticketResponseList;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Double getBookingTotalPrice() {
        return bookingTotalPrice;
    }

    public void setBookingTotalPrice(Double bookingTotalPrice) {
        this.bookingTotalPrice = bookingTotalPrice;
    }

}
