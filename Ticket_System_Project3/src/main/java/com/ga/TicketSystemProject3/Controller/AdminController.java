package com.ga.TicketSystemProject3.Controller;


import com.ga.TicketSystemProject3.Model.Ticket;
import com.ga.TicketSystemProject3.Service.TicketService;
import com.ga.TicketSystemProject3.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final TicketService ticketService;
    private final UserService userService;

    public AdminController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }
    
    @DeleteMapping("/users/softDelete")
    public void softDelete(){
        System.out.println("calling soft delete user in user controller ========>");
        userService.softDelete();
    }
    @GetMapping("/tickets/getAll")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        ticketService.softDeleteTicket(id);
        return ResponseEntity.ok("Ticket soft deleted successfully");
    }
}
