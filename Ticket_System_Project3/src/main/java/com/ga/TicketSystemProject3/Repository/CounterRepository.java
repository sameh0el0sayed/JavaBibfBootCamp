package com.ga.TicketSystemProject3.Repository;


import com.ga.TicketSystemProject3.Model.Counter;
import com.ga.TicketSystemProject3.Model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {

}
