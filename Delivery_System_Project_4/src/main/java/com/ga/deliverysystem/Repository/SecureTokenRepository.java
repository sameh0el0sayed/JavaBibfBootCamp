package com.ga.deliverysystem.Repository;

import com.ga.deliverysystem.Model.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken,Long> {
    SecureToken findByToken(final String token);
    Long removeByToken(final SecureToken token);
}

