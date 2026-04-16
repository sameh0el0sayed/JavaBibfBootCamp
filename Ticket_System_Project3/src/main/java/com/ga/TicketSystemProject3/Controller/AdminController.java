package com.ga.TicketSystemProject3.Controller;


import com.ga.TicketSystemProject3.Model.Ticket;
import com.ga.TicketSystemProject3.Service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final TicketService ticketService;

    public AdminController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("/ticket/getAll")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        ticketService.softDeleteTicket(id);
        return ResponseEntity.ok("Ticket soft deleted successfully");
    }
}
