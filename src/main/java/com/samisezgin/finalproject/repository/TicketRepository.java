package com.samisezgin.finalproject.repository;

import com.samisezgin.finalproject.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {

}
