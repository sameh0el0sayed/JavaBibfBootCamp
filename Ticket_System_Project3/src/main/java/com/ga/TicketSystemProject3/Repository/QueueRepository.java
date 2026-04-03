package com.ga.TicketSystemProject3.Repository;


import com.ga.TicketSystemProject3.Model.Queue;
import com.ga.TicketSystemProject3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {

}
