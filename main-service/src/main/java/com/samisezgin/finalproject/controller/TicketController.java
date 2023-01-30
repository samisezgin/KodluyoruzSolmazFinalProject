package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.response.TicketResponse;
import com.samisezgin.finalproject.service.impl.TicketServiceImpl;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketServiceImpl ticketService;

    public TicketController(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable Integer id) {
        return ticketService.getById(id);
    }

    @GetMapping("/user/{email}")
    public List<TicketResponse> getByEmail(@PathVariable String email) {
        return ticketService.getAllByUserEmail(email);
    }

    @PostMapping("/{voyageId}")
    public TicketResponse create(@PathVariable Integer voyageId, @RequestBody TicketRequest ticketRequest) {
        LoggerUtil.getLogger().log(Level.INFO, "TicketController POST request -> createTicket :" + ticketRequest.getCitizenshipNumber());
        return ticketService.create(voyageId, ticketRequest);
    }

    @PutMapping("/{ticketId}")
    public TicketResponse update(@PathVariable Integer ticketId, @RequestBody TicketRequest ticketRequest) {
        LoggerUtil.getLogger().log(Level.INFO, "TicketController POST request -> updateTicket :" + ticketRequest.getCitizenshipNumber());
        return ticketService.update(ticketId, ticketRequest);
    }

    @DeleteMapping("/{ticketId}")
    public TicketResponse delete(@PathVariable Integer ticketId) {
        LoggerUtil.getLogger().log(Level.INFO, "TicketController POST request -> deleteTicket :" + ticketService.getById(ticketId));
        return ticketService.delete(ticketId);
    }
}
