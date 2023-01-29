package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.request.VoyageRequest;
import com.samisezgin.finalproject.dto.response.BookingResponse;
import com.samisezgin.finalproject.dto.response.TicketResponse;
import com.samisezgin.finalproject.exceptions.EmptyTicketListException;
import com.samisezgin.finalproject.exceptions.MaleTicketLimitException;
import com.samisezgin.finalproject.exceptions.TicketListOverflowException;
import com.samisezgin.finalproject.exceptions.UserNotFoundException;
import com.samisezgin.finalproject.model.*;
import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.PassengerType;
import com.samisezgin.finalproject.model.enums.PaymentStatus;
import com.samisezgin.finalproject.model.enums.TravelType;
import com.samisezgin.finalproject.repository.BookingRepository;
import com.samisezgin.finalproject.repository.TicketRepository;
import com.samisezgin.finalproject.repository.UserRepository;
import com.samisezgin.finalproject.service.VoyageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {

    @InjectMocks
    BookingServiceImpl bookingService;

    @Mock
    UserRepository userRepository;

    @Mock
    VoyageService voyageService;
    @Mock
    ModelMapper modelMapper;

    @Mock
    BookingRepository bookingRepository;


    @Test
    public void getBookingById() {
        // Arrange
        Integer bookingId = 1;
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setActive(true);
        booking.setId(bookingId);

        Ticket ticket = new Ticket();
        ticket.setId(1);


        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);
        booking.setTicketList(ticketList);
        bookingRepository.save(booking);

        when(bookingRepository.findById(eq(bookingId))).thenReturn(Optional.of(booking));
        when(modelMapper.map(eq(booking),eq(BookingResponse.class))).thenReturn(new BookingResponse());
        when(modelMapper.map(eq(ticket),eq(TicketResponse.class))).thenReturn(new TicketResponse());

        // Act
        BookingResponse bookingResponse = bookingService.getById(bookingId);

        // Assert
        assertNotNull(bookingResponse);
        verify(bookingRepository).findById(eq(bookingId));
        verify(modelMapper).map(eq(booking),eq(BookingResponse.class));
        verify(modelMapper).map(eq(ticket),eq(TicketResponse.class));
    }

    @Test
    public void changeBookingPaymentStatus_shouldChangePaymentStatusToPaid() {
        // given
        Booking booking = new Booking();
        booking.setPaymentStatus(PaymentStatus.PENDING);
        when(bookingRepository.findById(anyInt())).thenReturn(java.util.Optional.of(booking));

        // when
        bookingService.changePaymentStatus(1);

        // then
        verify(bookingRepository, times(1)).findById(1);
        verify(bookingRepository, times(1)).save(booking);
        assertEquals(PaymentStatus.SUCCESS, booking.getPaymentStatus());
    }



















}