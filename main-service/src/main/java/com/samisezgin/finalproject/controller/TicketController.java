package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.response.TicketResponse;
import com.samisezgin.finalproject.service.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
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
        return ticketService.create(voyageId,ticketRequest);
    }

    @PutMapping("/{ticketId}")
    public TicketResponse update(@PathVariable Integer ticketId, @RequestBody TicketRequest ticketRequest)
    {
        return ticketService.update(ticketId,ticketRequest);
    }

    @DeleteMapping("/{ticketId}")
    public TicketResponse delete(@PathVariable Integer ticketId)
    {
        return ticketService.delete(ticketId);
    }
}
