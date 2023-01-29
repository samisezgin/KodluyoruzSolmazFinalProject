package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.response.TicketResponse;
import com.samisezgin.finalproject.dto.response.VoyageResponse;
import com.samisezgin.finalproject.model.Ticket;
import com.samisezgin.finalproject.model.Voyage;
import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.repository.TicketRepository;
import com.samisezgin.finalproject.repository.VoyageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TicketServiceImplTest {

    @InjectMocks
    TicketServiceImpl ticketService;
    @Mock
    VoyageRepository voyageRepository;
    @Mock
    TicketRepository ticketRepository;

    @Mock
    ModelMapper modelMapper;


    @Test
    public void testCreate() {
        // Arrange
        Voyage voyage = new Voyage();
        voyage.setId(1);
        voyage.setPrice(100.0);
        when(voyageRepository.findById(1)).thenReturn(Optional.of(voyage));
        TicketRequest request = new TicketRequest();
        request.setPassengerName("John Smith");
        request.setCitizenshipNumber("123456");
        request.setGender(Gender.MALE);
        request.setPassengerSurname("Smith");
        when(modelMapper.map(request, Ticket.class)).thenReturn(new Ticket());
        when(ticketRepository.save(any(Ticket.class))).thenReturn(new Ticket());
        when(modelMapper.map(any(Ticket.class), eq(TicketResponse.class))).thenReturn(new TicketResponse());

        // Act
        TicketResponse response = ticketService.create(1, request);

        // Assert
        assertNotNull(response);
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }


    @Test
    public void testUpdate() {
        // Arrange
        Ticket ticket = new Ticket();
        ticket.setId(1);
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        TicketRequest request = new TicketRequest();
        request.setPassengerName("John Smith");
        request.setCitizenshipNumber("123456");
        request.setGender(Gender.MALE);
        request.setPassengerSurname("Smith");
        when(ticketRepository.save(any(Ticket.class))).thenReturn(new Ticket());
        when(modelMapper.map(any(Ticket.class), eq(TicketResponse.class))).thenReturn(new TicketResponse());

        // Act
        TicketResponse response = ticketService.update(1, request);

        // Assert
        assertNotNull(response);
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }


    @Test
    public void testGetById() {
        // Arrange
        Ticket ticket = new Ticket();
        ticket.setId(1);
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        when(modelMapper.map(ticket, TicketResponse.class)).thenReturn(new TicketResponse());

        // Act
        TicketResponse response = ticketService.getById(1);

        // Assert
        assertNotNull(response);
    }
    @Test
    public void testDelete() {
        // given
        Integer id = 1;
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setPassengerName("sami");
        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
        // then
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setPassengerName("sami");

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
        when(modelMapper.map(ticket,TicketResponse.class)).thenReturn(ticketResponse);
        TicketResponse ticketResponse1 = ticketService.delete(id);
       assertEquals(ticketResponse1.getPassengerName(),ticket.getPassengerName());
    }

}