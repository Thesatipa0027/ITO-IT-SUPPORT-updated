package com.itsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsupport.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
