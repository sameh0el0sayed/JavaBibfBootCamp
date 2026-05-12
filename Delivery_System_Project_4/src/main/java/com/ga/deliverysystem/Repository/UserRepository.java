package com.ga.deliverysystem.Repository;
import com.ga.deliverysystem.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByEmailAddress(String userEmailAddress);
    UserModel findUserByEmailAddress(String userEmailAddress);
}

