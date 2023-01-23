package com.samisezgin.finalproject.service;

import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.response.BookingResponse;
import com.samisezgin.finalproject.model.Ticket;

import java.util.List;

public interface BookingService {



    BookingResponse create(BookingRequest bookingRequest);

    BookingResponse getById(Integer id);

}
