package com.ga.deliverysystem.Service;


import com.ga.deliverysystem.Model.SecureTokenModel;

public interface SecureTokenService {

    SecureTokenModel createToken();
    void saveSecureToken(SecureTokenModel secureTokenModel);
    SecureTokenModel findByToken(String token);
    void removeToken(SecureTokenModel token);

}
