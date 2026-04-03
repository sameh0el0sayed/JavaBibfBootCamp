package com.ga.TicketSystemProject3.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Counters")
public class Counter {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String status;

    @OneToOne(mappedBy = "counter", fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "counter", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
