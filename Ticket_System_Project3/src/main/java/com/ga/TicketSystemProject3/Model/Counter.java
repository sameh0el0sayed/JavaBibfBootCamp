package com.ga.TicketSystemProject3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Counters")
public class Counter {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CounterStatus status;

    @OneToOne(mappedBy = "counter", fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "counter", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ticket> ticketList;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CounterStatus getStatus() {
        return status;
    }

    public void setStatus(CounterStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
