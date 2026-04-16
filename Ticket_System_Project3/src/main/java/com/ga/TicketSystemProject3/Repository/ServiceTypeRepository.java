package com.ga.TicketSystemProject3.Repository;


import com.ga.TicketSystemProject3.Model.Queue;
import com.ga.TicketSystemProject3.Model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {

}
