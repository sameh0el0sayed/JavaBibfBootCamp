package com.ga.deliverysystem.Service;


import com.ga.deliverysystem.Model.SecureTokenModel;
import com.ga.deliverysystem.Repository.SecureTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import java.util.Base64;

import java.time.LocalDateTime;
@Service
public class DefaultSecureTokenService implements SecureTokenService{
    private static BytesKeyGenerator DEFAULT_TOKEN_GENERATOR= KeyGenerators.secureRandom(12);
    @Value("2800")
    private int tokenValidityInSeconds;

    @Autowired
    SecureTokenRepository secureTokenRepository;

    @Override
    public SecureTokenModel createToken() {
        String tokenValue = new String(Base64.getEncoder().encodeToString(DEFAULT_TOKEN_GENERATOR.generateKey()));
        SecureTokenModel secureTokenModel =new SecureTokenModel();
        secureTokenModel.setToken(tokenValue);
        secureTokenModel.setExpireAt(LocalDateTime.now().plusSeconds(tokenValidityInSeconds));
        this.saveSecureToken(secureTokenModel);
        return secureTokenModel;

    }

    @Override
    public void saveSecureToken(SecureTokenModel secureTokenModel) {
        secureTokenRepository.save(secureTokenModel);

    }

    @Override
    public SecureTokenModel findByToken(String token) {
        return secureTokenRepository.findByToken(token);
    }

    @Override
    public void removeToken(SecureTokenModel token) {
        secureTokenRepository.removeByToken(token);
    }
}


