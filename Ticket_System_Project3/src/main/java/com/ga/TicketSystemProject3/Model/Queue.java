package com.ga.TicketSystemProject3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Queue")
public class Queue {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long current_number;

    private long last_number;

    @OneToOne(
            fetch = FetchType.LAZY
            ,cascade = CascadeType.REFRESH
    )
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    @JsonIgnore
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public long getLast_number() {
        return last_number;
    }

    public void setLast_number(long last_number) {
        this.last_number = last_number;
    }

    public long getCurrent_number() {
        return current_number;
    }

    public void setCurrent_number(long current_number) {
        this.current_number = current_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
