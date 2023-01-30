package com.samisezgin.finalproject.service;


import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.response.TicketResponse;

import java.util.List;

public interface TicketService {

    TicketResponse create(Integer id, TicketRequest ticketRequest);

    TicketResponse update(Integer id, TicketRequest ticketRequest);

    TicketResponse getById(Integer id);

    TicketResponse delete(Integer id);

    List<TicketResponse> getAllByUserEmail(String email);
}
