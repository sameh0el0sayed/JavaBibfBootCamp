package com.ga.TicketSystemProject3.Controller;


import com.ga.TicketSystemProject3.Model.Ticket;
import com.ga.TicketSystemProject3.Service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket created = ticketService.createTicket(ticket);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/next")
    public ResponseEntity<Ticket> getNextTicket() {
        Ticket nextTicket = ticketService.getNextTicket();
        return ResponseEntity.ok(nextTicket);
    }
    @PutMapping("/{id}/close")
    public ResponseEntity<Ticket> closeTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.closeTicket(id));
    }
    @PutMapping("/{id}/reopen")
    public ResponseEntity<Ticket> reopenTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.reopenTicket(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket updatedTicket) {
        return ResponseEntity.ok(ticketService.updateTicket(updatedTicket));
    }

}
