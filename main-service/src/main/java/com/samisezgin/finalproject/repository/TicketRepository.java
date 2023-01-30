package com.samisezgin.finalproject.repository;

import com.samisezgin.finalproject.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
