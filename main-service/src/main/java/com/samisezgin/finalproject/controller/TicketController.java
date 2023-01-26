package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.response.TicketResponse;
import com.samisezgin.finalproject.exceptions.TicketNotFoundException;
import com.samisezgin.finalproject.repository.TicketRepository;
import com.samisezgin.finalproject.service.TicketService;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketRepository ticketRepository;


    public TicketController(TicketService ticketService,
                            TicketRepository ticketRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable Integer id) {
        return ticketService.getById(id);
    }

    @GetMapping("/user/{email}")
    public List<TicketResponse> getById(@PathVariable String email) {
        return ticketService.getAllByUserEmail(email);
    }

    @PostMapping("/{voyageId}")
    public TicketResponse create(@PathVariable Integer voyageId, @RequestBody TicketRequest ticketRequest)
    {
        LoggerUtil.getLogger().log(Level.INFO, "TicketController POST request -> createTicket :" + ticketRequest.getCitizenshipNumber());
        return ticketService.create(voyageId,ticketRequest);
    }

    @PutMapping("/{ticketId}")
    public TicketResponse update(@PathVariable Integer ticketId, @RequestBody TicketRequest ticketRequest)
    {
        LoggerUtil.getLogger().log(Level.INFO, "TicketController POST request -> updateTicket :" + ticketRequest.getCitizenshipNumber());
        return ticketService.update(ticketId,ticketRequest);
    }

    @DeleteMapping("/{ticketId}")
    public TicketResponse delete(@PathVariable Integer ticketId)
    {
        LoggerUtil.getLogger().log(Level.INFO, "TicketController POST request -> deleteTicket :" + ticketRepository.findById(ticketId).orElseThrow(()->new TicketNotFoundException("Ticket not found while trying to delete")).getCitizenshipNumber());
        return ticketService.delete(ticketId);
    }
}
