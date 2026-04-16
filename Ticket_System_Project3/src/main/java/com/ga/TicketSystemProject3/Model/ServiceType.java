package com.ga.TicketSystemProject3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jshell.execution.LocalExecutionControl;

import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "ServiceType")
public class ServiceType {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    private LocalTime avg_service_time;

    @OneToMany(mappedBy = "serviceType", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ticket> ticketList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getAvg_service_time() {
        return avg_service_time;
    }

    public void setAvg_service_time(LocalTime avg_service_time) {
        this.avg_service_time = avg_service_time;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
