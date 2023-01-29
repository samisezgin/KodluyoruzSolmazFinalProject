package com.samisezgin.finalproject.service;

import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.request.VoyageRequest;
import com.samisezgin.finalproject.dto.response.VoyageResponse;
import com.samisezgin.finalproject.model.Voyage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoyageService {

    VoyageResponse create(VoyageRequest voyageRequest);
    VoyageResponse deactivate(Integer id);
    VoyageResponse delete(Integer id);
    Integer getSoldTicketCount(Integer id);
    Double getTotalRevenue(Integer id);


    List<VoyageResponse> getByCityOrTravelTypeOrDate(String from, String to, String travelType, LocalDateTime dateTime);
    Voyage findVoyage(BookingRequest bookingRequest);

    Optional<Voyage> findById(Integer id);


}
