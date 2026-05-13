package com.ga.deliverysystem.Repository;

import com.ga.deliverysystem.Model.MerchantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantModel, UUID> {
    MerchantModel findByName(String name);
}
