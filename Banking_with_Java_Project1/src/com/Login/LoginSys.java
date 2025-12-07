package com.Login;

import com.Customer.CustomerModel;
import com.Customer.CustomerService;
import org.mindrot.jbcrypt.BCrypt;

public class LoginSys {
    static String  dfUsername="bibf";
    static String dfPassword="pass";

    public static boolean login(AuthModel _authModel){
        CustomerService customerService=new CustomerService();
        CustomerModel customer=customerService.findByUsernamePass(_authModel.getUserName(),_authModel.getPassword());

        String hpPassword=hashPassword(_authModel.Password); //for testing
        return   customer.getPasswordHash().equals(hpPassword);
    }

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
