package com.ga.deliverysystem.Repository;

import com.ga.deliverysystem.Model.SecureTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureTokenModel,Long> {
    SecureTokenModel findByToken(final String token);
    Long removeByToken(final SecureTokenModel token);
}

