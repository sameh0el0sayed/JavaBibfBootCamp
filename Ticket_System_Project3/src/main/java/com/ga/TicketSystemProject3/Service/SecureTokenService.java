package com.ga.TicketSystemProject3.Service;

import com.ga.TicketSystemProject3.Model.SecureToken;

public interface SecureTokenService {

    SecureToken createToken();
    void saveSecureToken(SecureToken secureToken);
    SecureToken findByToken(String token);
    void removeToken(SecureToken token);

}
