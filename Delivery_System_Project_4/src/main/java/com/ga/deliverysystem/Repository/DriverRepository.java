package com.ga.deliverysystem.Repository;

import com.ga.deliverysystem.Model.DriverModel;
import com.ga.deliverysystem.Model.Enum.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<DriverModel, UUID> {
    List<DriverModel> findByDriverStatus(DriverStatus driverStatus);
}
