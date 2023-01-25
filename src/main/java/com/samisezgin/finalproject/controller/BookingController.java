package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.response.BookingResponse;
import com.samisezgin.finalproject.service.BookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@SecurityRequirement(name = "techgeeknext-api")
public class BookingController {

private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public BookingResponse getById(@PathVariable Integer id)
    {
        return bookingService.getById(id);
    }

    @PostMapping
    public BookingResponse create(@RequestBody BookingRequest bookingRequest)
    {
        return bookingService.create(bookingRequest);
    }


}
