package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.response.TicketResponse;
import com.samisezgin.finalproject.exceptions.TicketNotFoundException;
import com.samisezgin.finalproject.exceptions.UserNotFoundException;
import com.samisezgin.finalproject.exceptions.VoyageNotFoundException;
import com.samisezgin.finalproject.model.Ticket;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.model.Voyage;
import com.samisezgin.finalproject.repository.TicketRepository;
import com.samisezgin.finalproject.repository.UserRepository;
import com.samisezgin.finalproject.repository.VoyageRepository;
import com.samisezgin.finalproject.service.TicketService;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final ModelMapper modelMapper;

    private final VoyageRepository voyageRepository;
    private final UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, VoyageRepository voyageRepository,
                             UserRepository userRepository) {

        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.voyageRepository = voyageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TicketResponse create(Integer voyageId, TicketRequest ticketRequest) {

        Voyage foundVoyage = voyageRepository.findById(voyageId).orElseThrow(() -> new VoyageNotFoundException("İlgili sefer bulunamadı!"));

        Ticket ticket = modelMapper.map(ticketRequest, Ticket.class);
        ticket.setVoyage(foundVoyage);
        ticket.setPrice(foundVoyage.getPrice());

        LoggerUtil.getLogger().log(Level.INFO, "TicketService  -> createTicket :" + ticketRequest.getPassengerName());
        ticketRepository.save(ticket);

        return modelMapper.map(ticket, TicketResponse.class);
    }

    @Override
    public TicketResponse update(Integer id, TicketRequest ticketRequest) {

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("İlgili bilet bulunamadı!"));

        ticket.setCitizenshipNumber(ticketRequest.getCitizenshipNumber());
        ticket.setPassengerName(ticketRequest.getPassengerName());
        ticket.setPassengerSurname(ticketRequest.getPassengerSurname());
        ticket.setGender(ticketRequest.getGender());

        ticketRepository.save(ticket);
        LoggerUtil.getLogger().log(Level.INFO, "TicketService  -> updateTicket :" + ticketRequest.getPassengerName());
        return modelMapper.map(ticket, TicketResponse.class);
    }

    @Override
    public TicketResponse getById(Integer id) {

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("İlgili bilet bulunamadı"));
        return modelMapper.map(ticket, TicketResponse.class);
    }


    @Override
    public TicketResponse delete(Integer id) {

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("İlgili bilet bulunamadı"));
        ticketRepository.delete(ticket);
        LoggerUtil.getLogger().log(Level.INFO, "TicketService  -> deleteTicket :" + ticket.getPassengerName());
        return modelMapper.map(ticket, TicketResponse.class);
    }

    @Override
    public List<TicketResponse> getAllByUserEmail(String email) {
        User passengerUser = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı."));

        var ticketList = passengerUser.getBookingList().stream().flatMap(booking -> booking.getTicketList().stream()).toList();

        return ticketList.stream().map(ticket -> modelMapper.map(ticket, TicketResponse.class)).toList();
    }
}
