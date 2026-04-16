package com.ga.TicketSystemProject3.Service;

import com.ga.TicketSystemProject3.Model.*;
import com.ga.TicketSystemProject3.Repository.CounterRepository;
import com.ga.TicketSystemProject3.Repository.QueueRepository;
import com.ga.TicketSystemProject3.Repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
 import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final QueueRepository queueRepository;
     private final ReentrantLock lock=new ReentrantLock();
    private final Semaphore semaphore=new Semaphore(0);
    private final CounterRepository counterRepository;
    public TicketService(TicketRepository ticketRepository, QueueRepository queueRepository, CounterRepository counterRepository) {
        this.ticketRepository = ticketRepository;
        this.queueRepository = queueRepository;
        this.counterRepository = counterRepository;
    }

    public Ticket createTicket(Ticket ticket){

        long count = queueRepository.count();

        com.ga.TicketSystemProject3.Model.Queue newQueue =
                new com.ga.TicketSystemProject3.Model.Queue();

        newQueue.setCurrent_number(count+1);
        newQueue.setLast_number(count);

        com.ga.TicketSystemProject3.Model.Queue queue= queueRepository.save(newQueue);

        lock.lock();
        ticket.setQueue(queue);
        ticket.setStatus(TicketStatus.WAITING);
        ticket.setCreated_at(LocalDateTime.now());
        ticket.setTicket_number(queue.getId()
        );
        Ticket savedTicket=ticketRepository.save(ticket);
        lock.unlock();

        semaphore.release();

        return savedTicket;
    }

    @Transactional
    public Ticket getNextTicket() {

        lock.lock();
        try {

            Queue queue = queueRepository.findAll()
                    .stream().filter(t-> t.getTicket() == null)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Queue not found"));


            long nextNumber = queue.getId();

             Ticket ticket = ticketRepository.findByTicketNumber(nextNumber)
                    .orElseThrow(() -> new RuntimeException("Ticket not found"));

             Counter counter = counterRepository.findAll()
                    .stream()
                    .filter(c -> c.getStatus() == CounterStatus.WORKING)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No working counter"));

            ticket.setStatus(TicketStatus.CALLED);
            ticket.setCalled_at(LocalDateTime.now());
            ticket.setCounter(counter);
            return ticketRepository.save(ticket);

        } finally {
            lock.unlock();
        }
    }
    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll().stream().toList();
    }

    public Ticket getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() == TicketStatus.CANCELLED) {
            throw new RuntimeException("Ticket is deleted");
        }

        return ticket;
    }

    public Ticket updateTicket(Ticket updatedTicket){
        Ticket ticket = getTicketById(updatedTicket.getId());
        ticket.setServiceType(updatedTicket.getServiceType());
        ticket.setStatus(updatedTicket.getStatus());
        ticket.setCounter(updatedTicket.getCounter());

        return ticketRepository.save(ticket);
    }

    public Ticket closeTicket(Long id) {

        Ticket ticket = getTicketById(id);

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new RuntimeException("Ticket already closed");
        }

         Queue queue = ticket.getQueue();

         ticket.setStatus(TicketStatus.CLOSED);
        ticket.setCounter(null);
        ticket.setQueue(null);

         Ticket savedTicket = ticketRepository.save(ticket);

         if (queue != null) {
            queueRepository.delete(queue);
        }

        return savedTicket;
    }

    public Ticket reopenTicket(Long id) {

        Ticket ticket = getTicketById(id);
        ticket.setStatus(TicketStatus.REOPENED);
        Counter counter = counterRepository.findAll()
                .stream()
                .filter(c -> c.getStatus() == CounterStatus.WORKING)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No working counter"));        ticket.setCounter(counter);
        return ticketRepository.save(ticket);
    }

    public void softDeleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticket.setStatus(TicketStatus.CANCELLED);
        ticketRepository.save(ticket);
        queueRepository.deleteById(ticket.getTicket_number());
     }
}
