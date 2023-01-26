package com.samisezgin.finalproject.client;

import com.samisezgin.finalproject.exceptions.BookingNotFoundException;
import com.samisezgin.finalproject.model.Booking;
import com.samisezgin.finalproject.model.Invoice;
import com.samisezgin.finalproject.model.NotificationRequest;
import com.samisezgin.finalproject.repository.BookingRepository;
import com.samisezgin.finalproject.service.BookingService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceClient {

    private final BookingRepository bookingRepository;
    private final BookingService bookingService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${payment.service.url}")
    private String paymentUrl;

    public PaymentServiceClient(BookingRepository bookingRepository, BookingService bookingService, RabbitTemplate rabbitTemplate) {
        this.bookingRepository = bookingRepository;
        this.bookingService = bookingService;
        this.rabbitTemplate = rabbitTemplate;
    }

    private static Invoice bookingToInvoice(String paymentType, Booking foundBooking) {
        Invoice invoice = new Invoice();
        invoice.setBookingId(foundBooking.getId());
        invoice.setEmail(foundBooking.getPassengerUser().getEmail());
        invoice.setName(foundBooking.getPassengerUser().getName());
        invoice.setSurname(foundBooking.getPassengerUser().getSurname());
        invoice.setPhoneNumber(foundBooking.getPassengerUser().getPhoneNumber());
        invoice.setPaymentType(paymentType);
        invoice.setPaymentTotal(foundBooking.getBookingTotalPrice());
        return invoice;
    }

    public Invoice processPayment(Integer bookingId, String paymentType) {
        Booking foundBooking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found!"));

        Invoice invoice = bookingToInvoice(paymentType, foundBooking);

        RestTemplate template = new RestTemplate();
        HttpEntity<Invoice> request = new HttpEntity<>(invoice);

        var response=template.postForObject(paymentUrl, request, Invoice.class);

        bookingService.changePaymentStatus(invoice.getBookingId());
        rabbitTemplate.convertAndSend("notification", new NotificationRequest("Thank you for your purchase. Have a very safe journey: "+response, "SMS",response.getPhoneNumber()));

        return response;
    }

}
