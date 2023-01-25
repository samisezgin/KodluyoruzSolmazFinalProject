package com.samisezgin.finalproject.service;

import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.response.BookingResponse;

public interface BookingService {



    BookingResponse create(BookingRequest bookingRequest);

    BookingResponse getById(Integer id);

}
