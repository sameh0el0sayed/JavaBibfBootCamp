package com.ga.deliverysystem.Repository;

import com.ga.deliverysystem.Model.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileModel, Long> {

}

