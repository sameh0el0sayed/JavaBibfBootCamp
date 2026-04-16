package com.ga.TicketSystemProject3.Repository;


import com.ga.TicketSystemProject3.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByTicketNumber(long nextNumber);
}
