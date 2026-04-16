package com.ga.TicketSystemProject3.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number")
    private Long ticketNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime called_at;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "queue_id", referencedColumnName = "id")
    private Queue queue;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "counter_id", referencedColumnName = "id")
    private Counter counter;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "serviceType_id", referencedColumnName = "id")
    private ServiceType serviceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicket_number() {
        return ticketNumber;
    }

    public void setTicket_number(Long ticket_number) {
        this.ticketNumber = ticket_number;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getCalled_at() {
        return called_at;
    }

    public void setCalled_at(LocalDateTime called_at) {
        this.called_at = called_at;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }



}
