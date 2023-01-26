package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.client.PaymentServiceClient;
import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.response.BookingResponse;
import com.samisezgin.finalproject.model.Invoice;
import com.samisezgin.finalproject.model.PaymentRequest;
import com.samisezgin.finalproject.service.BookingService;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;

@RestController
@RequestMapping("/bookings")
public class BookingController {


    private final BookingService bookingService;

    private final PaymentServiceClient paymentServiceClient;


    public BookingController(BookingService bookingService, PaymentServiceClient paymentServiceClient) {
        this.bookingService = bookingService;
        this.paymentServiceClient = paymentServiceClient;
    }

    @GetMapping("/{id}")
    public BookingResponse getById(@PathVariable Integer id) {
        return bookingService.getById(id);
    }

    @PostMapping
    public BookingResponse create(@RequestBody BookingRequest bookingRequest) {
        LoggerUtil.getLogger().log(Level.INFO, "BookingController POST request -> createBooking :" + bookingRequest.getPassengerUserEmail());
        return bookingService.create(bookingRequest);
    }

    @PostMapping("/payment")
    public ResponseEntity<Invoice> processPayment(@RequestBody PaymentRequest paymentRequest) {
        Invoice invoice = paymentServiceClient.processPayment(paymentRequest.getBookingId(), paymentRequest.getPaymentType());

        LoggerUtil.getLogger().log(Level.INFO, "BookingController POST request -> processPayment :" + invoice.getEmail());

        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }


}
